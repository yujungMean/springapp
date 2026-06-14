package com.app.springapp.service;

import com.app.springapp.domain.dto.request.GuestbookRereplyReportCreateRequestDTO;
import com.app.springapp.repository.GuestbookRereplyReportDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class GuestbookRereplyReportServiceImpl implements GuestbookRereplyReportService {

    private final GuestbookRereplyReportDAO guestbookRereplyReportDAO;

    //방명록 대댓글 신고 작성
    @Override
    public void write(GuestbookRereplyReportCreateRequestDTO guestbookRereplyReportCreateRequestDTO) {
        guestbookRereplyReportDAO.save(guestbookRereplyReportCreateRequestDTO);
    }
}
