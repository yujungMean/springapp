package com.app.springapp.service;

import com.app.springapp.domain.dto.request.GuestbookReplyReportCreateRequestDTO;
import com.app.springapp.repository.GuestbookReplyReportDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class GuestbookReplyReportServiceImpl implements GuestbookReplyReportService {

    private final GuestbookReplyReportDAO guestbookReplyReportDAO;

    //방명록 답글 신고 작성
    @Override
    public void write(GuestbookReplyReportCreateRequestDTO guestbookReplyReportCreateRequestDTO) {
        guestbookReplyReportDAO.save(guestbookReplyReportCreateRequestDTO);
    }
}
