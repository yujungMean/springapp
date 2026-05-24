package com.app.springapp.api;

import com.app.springapp.domain.dto.PostCreateDTO;
import com.app.springapp.domain.dto.request.*;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
@Slf4j
@Tag(name = "POST API", description = "게시글 API")
public class PostAPI {

    private final PostService postService;
    private final PostLikeService postLikeService;
    private final ReplyLikeService replyLikeService;
    private final ReplyService replyService;
    private final RereplyService rereplyService;

    //게시글 열람
    @PostMapping("/read")
    @Operation(summary = "게시글 열람 서비스", description = "게시글을 열람하는 서비스")
    @ApiResponse(responseCode = "201", description = "게시글 열람 완료")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    public ResponseEntity<ApiResponseDTO> readPost(@RequestBody PostReadRequestDTO postReadRequestDTO) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.of(
                        true,
                        "게시글 읽기 성공",
                        postService.getPostDetailInfo(postReadRequestDTO)));
    }

    //게시글 추가
    @PostMapping("/write")
    @Operation(summary = "게시글 작성 서비스", description = "게시글을 추가하는 서비스")
    @ApiResponse(responseCode = "201", description = "게시글 작성 완료")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    public ResponseEntity<ApiResponseDTO> writePost(@RequestBody PostCreateRequestDTO postCreateRequestDTO) {

        PostCreateDTO postCreateDTO = PostCreateDTO.from(postCreateRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.of(
                        true,
                        "게시글 작성 성공",
                        postService.writePost(postCreateDTO)));
    }



    //게시글 좋아요 등록
    @PostMapping("/apply-like")
    @Operation(summary = "게시글 좋아요 서비스", description = "게시글 좋아요 등록 서비스")
    @ApiResponse(responseCode = "201", description = "게시글 좋아요 완료")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    public ResponseEntity<ApiResponseDTO> applyLike(@RequestBody PostLikeRequestDTO postLikeRequestDTO) {

        log.info("{}", postLikeRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.of(
                        true,
                        "게시글 좋아요 등록 성공",
                        postLikeService.likePost(postLikeRequestDTO)));
    }

    //게시글 좋아요 취소
    @PostMapping("/cancel-like")
    @Operation(summary = "게시글 좋아요 취소 서비스", description = "게시글 좋아요 취소 서비스")
    @ApiResponse(responseCode = "201", description = "게시글 좋아요 취소 완료")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    public ResponseEntity<ApiResponseDTO> cancelLike(@RequestBody PostLikeRequestDTO postLikeRequestDTO) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.of(
                        true,
                        "게시글 좋아요 취소 성공",
                        postLikeService.cancelPostLike(postLikeRequestDTO)));
    }

    //댓글 좋아요
    @PostMapping("/like-reply")
    @Operation(summary = "댓글 좋아요 서비스", description = "댓글 좋아요 서비스")
    @ApiResponse(responseCode = "201", description = "댓글 좋아요 완료")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    public ResponseEntity<ApiResponseDTO> applyReplyLike(@RequestBody ReplyLikeRequestDTO replyLikeRequestDTO) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.of(
                        true,
                        "댓글 좋아요 성공",
                        replyLikeService.likeReply(replyLikeRequestDTO)));
    }

    //댓글 좋아요 취소
    @PostMapping("/cancel-like-reply")
    @Operation(summary = "댓글 좋아요 취소 서비스", description = "댓글 좋아요 취소 서비스")
    @ApiResponse(responseCode = "201", description = "댓글 좋아요 취소 완료")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    public ResponseEntity<ApiResponseDTO> cancelReplyLike(@RequestBody ReplyLikeRequestDTO replyLikeRequestDTO) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.of(
                        true,
                        "댓글 좋아요 취소 성공",
                        replyLikeService.cancelReplyLike(replyLikeRequestDTO)));
    }

    //댓글 작성
    @PostMapping("/write-reply")
    @Operation(summary = "댓글 작성 서비스", description = "댓글을 작성하는 서비스")
    @ApiResponse(responseCode = "201", description = "댓글 작성 완료")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    public ResponseEntity<ApiResponseDTO> writeReply(@RequestBody ReplyCreateRequestDTO replyCreateRequestDTO) {

        replyService.writeReply(replyCreateRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.of(
                        true,
                        "댓글 작성 성공"));
    }

    //댓글 수정
    @PutMapping("/update-reply")
    @Operation(summary = "댓글 수정 서비스", description = "댓글을 수정하는 서비스")
    @ApiResponse(responseCode = "201", description = "댓글 수정 완료")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    public ResponseEntity<ApiResponseDTO> updateRereply(@RequestBody ReplyUpdateRequestDTO replyUpdateRequestDTO) {

        replyService.updateReply(replyUpdateRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.of(
                        true,
                        "댓글 수정 성공"));
    }

    //댓글 삭제
    @DeleteMapping("/delete-reply/{id}")
    @Operation(summary = "댓글 삭제 서비스", description = "댓글id로 댓글을 삭제하는 서비스")
    @ApiResponse(responseCode = "200", description = "댓글 삭제 완료")
    @ApiResponse(responseCode = "404", description = "댓글 없음")
    @Parameter(
            name = "id",
            description = "댓글 번호",
            required = true,
            in = ParameterIn.PATH,
            example = "1",
            schema = @Schema(type = "number")
    )
    public ResponseEntity<ApiResponseDTO> deleteReply(@PathVariable Long id) {

        replyService.deleteReply(id);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.of(
                        true,
                        "댓글 삭제 성공"));
    }

    //대댓글 작성
    @PostMapping("/write-rereply")
    @Operation(summary = "대댓글 작성 서비스", description = "대댓글을 작성하는 서비스")
    @ApiResponse(responseCode = "201", description = "대댓글 작성 완료")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    public ResponseEntity<ApiResponseDTO> writeRereply(@RequestBody RereplyCreateRequestDTO rereplyCreateRequestDTO) {

        rereplyService.writeRereply(rereplyCreateRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.of(
                        true,
                        "대댓글 작성 성공"));
    }

    //대댓글 수정
    @PutMapping("/update-rereply")
    @Operation(summary = "대댓글 수정 서비스", description = "대댓글을 수정하는 서비스")
    @ApiResponse(responseCode = "201", description = "대댓글 수정 완료")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    public ResponseEntity<ApiResponseDTO> updateRereply(@RequestBody RereplyUpdateRequestDTO rereplyUpdateRequestDTO) {

        rereplyService.updateRereply(rereplyUpdateRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.of(
                        true,
                        "대댓글 수정 성공"));
    }

    //대댓글 삭제
    @DeleteMapping("/delete-rereply/{id}")
    @Operation(summary = "대댓글 삭제 서비스", description = "대댓글id로 대댓글을 삭제하는 서비스")
    @ApiResponse(responseCode = "200", description = "대댓글 삭제 완료")
    @ApiResponse(responseCode = "404", description = "대댓글 없음")
    @Parameter(
            name = "id",
            description = "대댓글 번호",
            required = true,
            in = ParameterIn.PATH,
            example = "1",
            schema = @Schema(type = "number")
    )
    public ResponseEntity<ApiResponseDTO> deleteRereply(@PathVariable Long id) {

        rereplyService.deleteRereply(id);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.of(
                        true,
                        "대댓글 삭제 성공"));
    }
}
