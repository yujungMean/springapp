package com.app.springapp.service;

import com.app.springapp.domain.dto.PostCreateDTO;
import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PostTests {

    @Autowired
    private PostService postService;

    @Test
    public void findPostByMemberIdandIdTest() {
        PostReadRequestDTO postReadRequestDTO = new PostReadRequestDTO();
        postReadRequestDTO.setMemberId(2L);
        postReadRequestDTO.setPostId(20L);
        log.info("Post Read RequestDTO : {}", postService.FindPost(postReadRequestDTO));
    }

    @Test
    public void findBeforePostTest() {
        log.info("{}", postService.findBeforePost(1L));
    }

    @Test
    public void findAfterPostTest() {
        log.info("{}", postService.findAfterPost(20L));
    }

    @Test
    public void countPostTest() {
        log.info("{}", postService.countPost(3L));
    }

    //2단계 테스트
    @Test
    public void findPostDetailTest() {
        PostReadRequestDTO postReadRequestDTO = new PostReadRequestDTO();
        postReadRequestDTO.setPostId(20L);
        postReadRequestDTO.setMemberId(2L);
        log.info("Post Read RequestDTO : {}", postService.getPostDetailInfo(postReadRequestDTO));
    }

    @Test
    public void writePostTest() {
        PostCreateDTO postCreateDTO = new PostCreateDTO();
        postCreateDTO.setPostTitle("테스트 제목");
        postCreateDTO.setPostContent("테스트 내용");
        postCreateDTO.setMemberId(2L);
        postCreateDTO.setCategoryId(2L);

        log.info("추가된 게시글 응답 정보 : {}",postService.writePost(postCreateDTO));
    }
}
