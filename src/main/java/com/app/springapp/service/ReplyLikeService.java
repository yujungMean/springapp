package com.app.springapp.service;

import com.app.springapp.domain.dto.request.ReplyLikeRequestDTO;
import com.app.springapp.domain.dto.response.ReplyLikeResponseDTO;

public interface ReplyLikeService {

    // 댓글 좋아요
    public ReplyLikeResponseDTO likeReply(ReplyLikeRequestDTO replyLikeRequestDTO);

    // 댓글 좋아요 취소
    public ReplyLikeResponseDTO cancelReplyLike(ReplyLikeRequestDTO replyLikeRequestDTO);

    //댓글 모든 좋아요 삭제
    public void deleteById(Long replyId);

    // 해당 댓글 좋아요 조회
    public ReplyLikeResponseDTO findReplyLikeCountAndIsLiked(ReplyLikeRequestDTO replyLikeRequestDTO);

}
