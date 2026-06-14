package com.app.springapp.mapper;

import com.app.springapp.domain.dto.request.GuestbookReplyReportCreateRequestDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GuestbookReplyReportMapper {
    public void insert(GuestbookReplyReportCreateRequestDTO guestbookReplyReportCreateRequestDTO);
}
