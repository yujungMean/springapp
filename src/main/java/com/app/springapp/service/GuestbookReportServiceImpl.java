package com.app.springapp.service;

import com.app.springapp.domain.dto.request.GuestbookReportCreateRequestDTO;
import com.app.springapp.repository.GuestbookReportDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class GuestbookReportServiceImpl implements GuestbookReportService {

    private final GuestbookReportDAO guestbookReportDAO;

    //방명록 신고 작성
    @Override
    public void write(GuestbookReportCreateRequestDTO guestbookReportCreateRequestDTO) {
        guestbookReportDAO.save(guestbookReportCreateRequestDTO);
    }
}
