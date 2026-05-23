package com.app.springapp.mapper;

import com.app.springapp.domain.dto.request.ReplyLikeRequestDTO;
import com.app.springapp.domain.dto.response.ReplyLikeResponseDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyLikeMapper {

    //댓글 좋아요 등록
    public void insert(ReplyLikeRequestDTO replyLikeRequestDTO);

    //댓글 좋아요 취소
    public void delete(ReplyLikeRequestDTO replyLikeRequestDTO);

    //댓글 모든 좋아요 삭제
    public void deleteById(Long id);

    //댓글 좋아요 정보 불러오기 (좋아요수 로그인유저가 이 댓글을 좋아요를 했는지 여부)
    public ReplyLikeResponseDTO selectLikeCountAndIsLiked(ReplyLikeRequestDTO replyLikeRequestDTO);
}
