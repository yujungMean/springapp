package com.app.springapp.service;

import com.app.springapp.domain.dto.response.ChronologyAnalysisResponseDTO;
import com.app.springapp.repository.ChronologyDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChronologyServiceImpl implements ChronologyService {

    private final ChronologyDAO chronologyDAO;

    @Override
    public ChronologyAnalysisResponseDTO getAnalysis(Long projectId) {
        String nickname = chronologyDAO.findNicknameByProjectId(projectId);
        if (nickname == null) nickname = "사용자";

        int totalChecklists = chronologyDAO.countCompletedChecklistsByProjectId(projectId);
        int avgUserChecklists = chronologyDAO.findAvgCompletedChecklists();
        int avgDays = chronologyDAO.findAvgProjectDays();
        int projectCount = chronologyDAO.findAvgProjectCount();

        int membersWithMore = chronologyDAO.countMembersWithMoreChecklists(totalChecklists);
        int totalMembers = chronologyDAO.countMembersWithChecklists();
        double percentile = totalMembers > 0
                ? Math.round((double) membersWithMore / totalMembers * 100 * 10) / 10.0
                : 50.0;

        List<Map<String, Object>> rawTop3 = chronologyDAO.findTop3ChecklistsByProjectId(projectId);
        List<ChronologyAnalysisResponseDTO.ChecklistStat> top3 = rawTop3 == null
                ? Collections.emptyList()
                : rawTop3.stream()
                        .map(row -> {
                            String text = String.valueOf(row.getOrDefault("CHECKLIST_TEXT", ""));
                            int count = row.get("CHECKLIST_COUNT") != null
                                    ? ((Number) row.get("CHECKLIST_COUNT")).intValue() : 0;
                            return new ChronologyAnalysisResponseDTO.ChecklistStat(text, count);
                        })
                        .collect(Collectors.toList());

        return new ChronologyAnalysisResponseDTO(
                nickname, percentile, totalChecklists, avgUserChecklists, top3, avgDays, projectCount
        );
    }
}
