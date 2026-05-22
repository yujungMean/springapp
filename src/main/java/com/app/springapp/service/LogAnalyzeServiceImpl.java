package com.app.springapp.service;

import com.app.springapp.domain.dto.request.LogAnalyzeRequestDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.domain.dto.response.LangchainResponseDTO;
import com.app.springapp.domain.vo.*;
import com.app.springapp.mapper.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogAnalyzeServiceImpl implements LogAnalyzeService {

    private final LogMapper logMapper;
    private final LogResultMapper logResultMapper;
    private final RadarScoreMapper radarScoreMapper;
    private final LogPatternMapper logPatternMapper;
    private final LogActionPlanMapper logActionPlanMapper;

    @Value("${langchain.server.url:http://localhost:8000}")
    private String langchainServerUrl;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResponseDTO analyzeLog(LogAnalyzeRequestDTO request, Long memberId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = langchainServerUrl + "/api/analyze";

        // 1. LangChain API 요청 파라미터 구성
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", request.getTitle());
        payload.put("category", request.getCategory());
        payload.put("vision", request.getVision());
        payload.put("content", request.getContent());
        payload.put("style", request.getStyle());
        payload.put("pastLogs", request.getPastLogs());

        log.info("Sending request to LangChain: {}", payload);

        try {
            // 2. LangChain 호출
            ResponseEntity<LangchainResponseDTO> response = restTemplate.postForEntity(url, payload, LangchainResponseDTO.class);
            LangchainResponseDTO aiResult = response.getBody();

            if (aiResult == null) {
                throw new RuntimeException("LangChain 응답이 비어있습니다.");
            }

            // 3. 로그 마스터 테이블 (TBL_LOG) 저장 (상태는 PUBLISHED)
            LogVO logVO = new LogVO();
            logVO.setLogTitle(request.getTitle());
            logVO.setVisionTitle(request.getVision());
            logVO.setLogContent(request.getContent());
            logVO.setLogThumbnailUrl(request.getLogThumbnailUrl());
            logVO.setCategoryId(request.getCategoryId());
            logVO.setLogStatus("PUBLISHED");
            logVO.setLogProgress(0); // TODO: 추후 프로그레스 로직
            logVO.setMemberId(memberId);
            
            logMapper.insert(logVO);
            Long savedLogId = logVO.getId();

            // 4. 분석 결과 마스터 (TBL_LOG_RESULT) 저장
            LogResultVO logResultVO = new LogResultVO();
            logResultVO.setLogId(savedLogId);
            logResultVO.setLogResultFailureType(aiResult.getFailureType());
            logResultVO.setLogResultFailureTitle(aiResult.getFailureTitle());
            logResultVO.setLogResultFailureDesc(aiResult.getFailureDesc());
            logResultVO.setLogResultOneLineSummary(aiResult.getOneLineSummary());
            logResultVO.setLogResultOneLineSub(aiResult.getOneLineSub());
            logResultVO.setLogResultExternalRatio(aiResult.getExternalRatio());
            logResultVO.setLogResultInternalRatio(aiResult.getInternalRatio());
            logResultVO.setLogResultFlowSituation(aiResult.getFlowSituation());
            logResultVO.setLogResultFlowDecision(aiResult.getFlowDecision());
            logResultVO.setLogResultFlowResult(aiResult.getFlowResult());
            logResultVO.setLogResultLogKeyDecision(aiResult.getLogKeyDecision());
            logResultVO.setLogResultLogExternalFactor(aiResult.getLogExternalFactor());
            logResultVO.setLogResultLogInternalFactor(aiResult.getLogInternalFactor());
            
            logResultMapper.insert(logResultVO);
            Long savedResultId = logResultVO.getId();

            // 5. 레이더 스코어 (TBL_RADAR_SCORE) 저장
            if (aiResult.getRadarScores() != null) {
                aiResult.getRadarScores().forEach((component, score) -> {
                    RadarScoreVO radarScoreVO = new RadarScoreVO();
                    radarScoreVO.setRadarComponent(component);
                    radarScoreVO.setRadarScore(score);
                    radarScoreVO.setLogResultId(savedResultId);
                    radarScoreMapper.insert(radarScoreVO);
                });
            }

            // 6. 실패 패턴 (TBL_LOG_PATTERN) 저장
            if (aiResult.getPatterns() != null) {
                for (LangchainResponseDTO.PatternDTO p : aiResult.getPatterns()) {
                    LogPatternVO patternVO = new LogPatternVO();
                    patternVO.setPatternType(p.getPatternType());
                    patternVO.setPatternTitle(p.getPatternTitle());
                    patternVO.setPatternContent(p.getPatternContent());
                    patternVO.setLogResultId(savedResultId);
                    logPatternMapper.insert(patternVO);
                }
            }

            // 7. 액션 플랜 (TBL_LOG_ACTION_PLAN) 저장
            if (aiResult.getActionPlans() != null) {
                // TRY 플랜
                if (aiResult.getActionPlans().getTryPlan() != null) {
                    LangchainResponseDTO.TryPlanDTO tp = aiResult.getActionPlans().getTryPlan();
                    LogActionPlanVO tryPlanVO = new LogActionPlanVO();
                    tryPlanVO.setLogActionPlanType("TRY");
                    tryPlanVO.setLogActionPlanTitle(tp.getTitle());
                    tryPlanVO.setLogActionPlanContent(tp.getContent());
                    tryPlanVO.setLogResultId(savedResultId);
                    logActionPlanMapper.insert(tryPlanVO);
                }

                // CHANGE 플랜
                if (aiResult.getActionPlans().getChangePlan() != null) {
                    LangchainResponseDTO.ChangePlanDTO cp = aiResult.getActionPlans().getChangePlan();
                    LogActionPlanVO changePlanVO = new LogActionPlanVO();
                    changePlanVO.setLogActionPlanType("CHANGE");
                    changePlanVO.setLogActionPlanTitle(cp.getTitle());
                    changePlanVO.setLogActionPlanCurrentPattern(cp.getCurrentPattern());
                    changePlanVO.setLogActionPlanCurrentContent(cp.getCurrentContent());
                    changePlanVO.setLogActionPlanImprovedPattern(cp.getImprovedPattern());
                    changePlanVO.setLogActionPlanImprovedContent(cp.getImprovedContent());
                    changePlanVO.setLogResultId(savedResultId);
                    logActionPlanMapper.insert(changePlanVO);
                }
            }

            return ApiResponseDTO.of(true, "로그 분석 및 저장 성공", savedLogId);

        } catch (Exception e) {
            log.error("LangChain 연동 중 오류 발생", e);
            throw new RuntimeException("로그 분석에 실패했습니다.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponseDTO getLogAnalyzeResult(Long logId) {
        // 1. 기본 로그 정보 조회
        com.app.springapp.domain.dto.response.LogResponseDTO logInfo = 
            logMapper.selectById(logId).orElseThrow(() -> new RuntimeException("로그를 찾을 수 없습니다."));

        // 2. 분석 결과 (TBL_LOG_RESULT) 조회
        LogResultVO aiResult = logResultMapper.findByLogId(logId);

        // 3. 응답 DTO 조립
        com.app.springapp.domain.dto.response.LogAnalyzeResultResponseDTO responseDto = 
            new com.app.springapp.domain.dto.response.LogAnalyzeResultResponseDTO();
        responseDto.setLogInfo(logInfo);
        responseDto.setAiResult(aiResult);

        if (aiResult != null) {
            responseDto.setRadarScores(radarScoreMapper.findByLogResultId(aiResult.getId()));
            responseDto.setLogPatterns(logPatternMapper.findByLogResultId(aiResult.getId()));
            responseDto.setLogActionPlans(logActionPlanMapper.findByLogResultId(aiResult.getId()));
        }

        return ApiResponseDTO.of(true, "로그 분석 결과 조회 성공", responseDto);
    }
}
