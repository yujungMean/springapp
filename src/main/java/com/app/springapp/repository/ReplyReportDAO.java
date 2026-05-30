package com.app.springapp.repository;

import com.app.springapp.domain.dto.request.PostReportCreateRequestDTO;
import com.app.springapp.mapper.ReplyReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReplyReportDAO {

    private final ReplyReportMapper replyReportMapper;

    public void save(PostReportCreateRequestDTO postReportCreateRequestDTO) {
        replyReportMapper.insert(postReportCreateRequestDTO);
    }
}
