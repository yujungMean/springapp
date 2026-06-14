package com.app.springapp.repository;

import com.app.springapp.domain.dto.request.GuestbookReportCreateRequestDTO;
import com.app.springapp.mapper.GuestbookReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GuestbookReportDAO {

    private final GuestbookReportMapper guestbookReportMapper;

    public void save(GuestbookReportCreateRequestDTO guestbookReportCreateRequestDTO) {
        guestbookReportMapper.insert(guestbookReportCreateRequestDTO);
    }
}
