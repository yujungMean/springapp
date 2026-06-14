package com.app.springapp.repository;

import com.app.springapp.domain.dto.request.GuestbookRereplyReportCreateRequestDTO;
import com.app.springapp.mapper.GuestbookRereplyReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GuestbookRereplyReportDAO {

    private final GuestbookRereplyReportMapper guestbookRereplyReportMapper;

    public void save(GuestbookRereplyReportCreateRequestDTO guestbookRereplyReportCreateRequestDTO) {
        guestbookRereplyReportMapper.insert(guestbookRereplyReportCreateRequestDTO);
    }
}
