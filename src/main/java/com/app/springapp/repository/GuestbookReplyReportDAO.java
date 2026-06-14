package com.app.springapp.repository;

import com.app.springapp.domain.dto.request.GuestbookReplyReportCreateRequestDTO;
import com.app.springapp.mapper.GuestbookReplyReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GuestbookReplyReportDAO {

    private final GuestbookReplyReportMapper guestbookReplyReportMapper;

    public void save(GuestbookReplyReportCreateRequestDTO guestbookReplyReportCreateRequestDTO) {
        guestbookReplyReportMapper.insert(guestbookReplyReportCreateRequestDTO);
    }
}
