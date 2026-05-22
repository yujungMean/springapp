package com.app.springapp.mapper;

import com.app.springapp.domain.dto.response.LogListResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// 로그 관련 SQL을 처리하는 Mapper 인터페이스
@Mapper
public interface LogMapper {

    // 전체 로그 목록 조회 (정렬 기준, 페이징 적용)
    public List<LogListResponseDTO> selectLogList(
            @Param("offset") int offset,
            @Param("size") int size,
            @Param("sort") String sort
    );

    // 키워드로 로그 목록 조회 (제목 기준 검색, 페이징 적용)
    public List<LogListResponseDTO> selectLogListByKeyword(
            @Param("keyword") String keyword,
            @Param("offset") int offset,
            @Param("size") int size,
            @Param("sort") String sort
    );

    // 카테고리로 로그 목록 조회 (페이징 적용)
    public List<LogListResponseDTO> selectLogListByCategory(
            @Param("category") String category,
            @Param("offset") int offset,
            @Param("size") int size,
            @Param("sort") String sort
    );

    // 회원 ID로 내 로그 목록 전체 조회
    List<LogListResponseDTO> findAllByMemberId(Long memberId);
}