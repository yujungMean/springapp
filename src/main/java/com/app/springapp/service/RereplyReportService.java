package com.app.springapp.service;

import com.app.springapp.domain.dto.request.PostReportCreateRequestDTO;

//대댓글 신고
public interface RereplyReportService {
    public void write(PostReportCreateRequestDTO postReportCreateRequestDTO);
}
