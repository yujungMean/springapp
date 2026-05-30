package com.app.springapp.repository;

import com.app.springapp.domain.dto.request.PostReportCreateRequestDTO;
import com.app.springapp.mapper.RereplyReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RereplyReportDAO {

    private final RereplyReportMapper rereplyReportMapper;

    public void save(PostReportCreateRequestDTO postReportCreateRequestDTO) {
        rereplyReportMapper.insert(postReportCreateRequestDTO);
    }
}
