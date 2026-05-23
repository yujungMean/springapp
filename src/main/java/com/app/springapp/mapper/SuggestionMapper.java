package com.app.springapp.mapper;

import com.app.springapp.domain.vo.SuggestionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SuggestionMapper {
    // 제안 저장
    void insert(SuggestionVO suggestionVO);

    // 프로젝트별 제안 목록 조회
    List<SuggestionVO> findAllByProjectId(Long projectId);
}