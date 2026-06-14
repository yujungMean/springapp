package com.app.springapp.service;

import com.app.springapp.domain.dto.request.GuestbookReportCreateRequestDTO;

public interface GuestbookReportService {
    void write(GuestbookReportCreateRequestDTO guestbookReportCreateRequestDTO);
}
