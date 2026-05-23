package com.app.springapp.service;

import com.app.springapp.domain.dto.request.ReplyLikeRequestDTO;
import com.app.springapp.domain.dto.response.ReplyLikeResponseDTO;
import com.app.springapp.exception.ReplyLikeException;
import com.app.springapp.repository.ReplyLikeDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class ReplyLikeServiceImpl implements ReplyLikeService {

    private final ReplyLikeDAO replyLikeDAO;

    // 게시글 좋아요
    @Override
    public ReplyLikeResponseDTO likeReply(ReplyLikeRequestDTO replyLikeRequestDTO) {
        replyLikeDAO.save(replyLikeRequestDTO);
        return findReplyLikeCountAndIsLiked(replyLikeRequestDTO);
    }

    // 댓글 좋아요 취소
    @Override
    public ReplyLikeResponseDTO cancelReplyLike(ReplyLikeRequestDTO replyLikeRequestDTO) {
        replyLikeDAO.delete(replyLikeRequestDTO);
        return findReplyLikeCountAndIsLiked(replyLikeRequestDTO);
    }

    @Override
    public void deleteById(Long replyId) {
        replyLikeDAO.deleteById(replyId);
    }

    // 게시글 좋아요 조회
    @Override
    public ReplyLikeResponseDTO findReplyLikeCountAndIsLiked(ReplyLikeRequestDTO replyLikeRequestDTO) {
        return replyLikeDAO.findReplyLikeCountAndIsLiked(replyLikeRequestDTO).orElseThrow(() -> new ReplyLikeException("게시글 좋아요, 좋아요 여부를 불러오지 못했습니다.", HttpStatus.BAD_REQUEST));
    }
}
