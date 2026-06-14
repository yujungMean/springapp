package com.app.springapp.mapper;

import com.app.springapp.domain.dto.request.GuestbookRereplyReportCreateRequestDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GuestbookRereplyReportMapper {
    public void insert(GuestbookRereplyReportCreateRequestDTO guestbookRereplyReportCreateRequestDTO);
}
