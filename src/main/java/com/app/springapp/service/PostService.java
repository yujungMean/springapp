package com.app.springapp.service;

import com.app.springapp.domain.dto.PostCreateDTO;
import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.domain.dto.response.*;

public interface PostService {

    //게시글 id로 게시글 정보 불러오기 + (memberId로 해당 게시글 좋아요 여부확인 가능)
    //게시글 리스트, 게시글 열람페이지에서 사용된다.
    public PostResponseDTO FindPost(PostReadRequestDTO postReadRequestDTO);

    // POST ID로 이전글 찾기
    public PostBeforeResponseDTO findBeforePost(Long postId);

    //POST ID로 다음글 찾기
    public PostAfterResponseDTO findAfterPost(Long postId);

    //작성자가 작성한 게시글 수
    public Integer countPost(Long memberId);

    //게시판 상세페이지에 모든 정보 불러오기
    public PostReadResponseDTO getPostDetailInfo(PostReadRequestDTO postReadRequestDTO);

    //게시글 작성 (PostCreateResponseDTO는 새로 작성된 게시글 번호정보가 들어있다.)
    public PostCreateResponseDTO writePost(PostCreateDTO postCreateDTO);
}
