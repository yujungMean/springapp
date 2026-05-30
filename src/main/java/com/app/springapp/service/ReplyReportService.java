package com.app.springapp.service;

import com.app.springapp.domain.dto.request.PostReportCreateRequestDTO;

//댓글 신고
public interface ReplyReportService {
    public void write(PostReportCreateRequestDTO postReportCreateRequestDTO);
}
