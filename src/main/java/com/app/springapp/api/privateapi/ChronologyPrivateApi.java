package com.app.springapp.api.privateapi;

import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.domain.dto.response.ChronologyAnalysisResponseDTO;
import com.app.springapp.service.ChronologyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "성장 연대기 비공개 API", description = "성과 분석 (JWT 필요)")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/private/chronology")
public class ChronologyPrivateApi {

    private final ChronologyService chronologyService;

    @Operation(summary = "성과 분석 조회", description = "프로젝트 ID로 성과 분석 데이터를 조회합니다.")
    @GetMapping("/analysis/{projectId}")
    public ResponseEntity<ApiResponseDTO<ChronologyAnalysisResponseDTO>> getAnalysis(
            @PathVariable Long projectId) {
        ChronologyAnalysisResponseDTO result = chronologyService.getAnalysis(projectId);
        return ResponseEntity.ok(ApiResponseDTO.of(true, "성과 분석 조회 성공", result));
    }
}
