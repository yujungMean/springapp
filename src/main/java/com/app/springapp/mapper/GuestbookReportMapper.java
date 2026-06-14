package com.app.springapp.mapper;

import com.app.springapp.domain.dto.request.GuestbookReportCreateRequestDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GuestbookReportMapper {
    public void insert(GuestbookReportCreateRequestDTO guestbookReportCreateRequestDTO);
}
