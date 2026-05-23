package com.app.springapp.service;

import com.app.springapp.domain.dto.response.ChronologyAnalysisResponseDTO;

public interface ChronologyService {

    ChronologyAnalysisResponseDTO getAnalysis(Long projectId);
}
