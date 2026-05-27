package com.app.springapp.mapper;

import com.app.springapp.domain.dto.request.PostLikeRequestDTO;
import com.app.springapp.domain.dto.response.PostLikeResponseDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostLikeMapper {
    public void insert(PostLikeRequestDTO postLikeRequestDTO);
    public PostLikeResponseDTO selectLikeCountAndIsLiked(PostLikeRequestDTO postLikeRequestDTO);

    //해당 멤버가 게시글 좋아요 1개 삭제
    public void delete(PostLikeRequestDTO postLikeRequestDTO);

    //게시글 모든 좋아요 삭제
    public void deleteByPostId(Long id);
}
