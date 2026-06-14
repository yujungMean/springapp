package com.app.springapp.api;

import com.app.springapp.domain.dto.request.GuestbookReportCreateRequestDTO;
import com.app.springapp.domain.dto.request.GuestbookReplyReportCreateRequestDTO;
import com.app.springapp.domain.dto.request.GuestbookRereplyReportCreateRequestDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.service.GuestbookReplyReportService;
import com.app.springapp.service.GuestbookRereplyReportService;
import com.app.springapp.service.GuestbookReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guestbook/report")
@Slf4j
@Tag(name = "Guestbook Report API", description = "방명록 신고 API")
public class GuestbookReportAPI {

    private final GuestbookReportService guestbookReportService;
    private final GuestbookReplyReportService guestbookReplyReportService;
    private final GuestbookRereplyReportService guestbookRereplyReportService;

    //방명록 신고
    @PostMapping("/comment")
    @Operation(summary = "방명록 신고 서비스", description = "방명록을 신고하는 서비스")
    @ApiResponse(responseCode = "201", description = "방명록 신고 완료")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    public ResponseEntity<ApiResponseDTO> guestbookReport(@RequestBody GuestbookReportCreateRequestDTO guestbookReportCreateRequestDTO) {

        guestbookReportService.write(guestbookReportCreateRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.of(
                        true,
                        "방명록 신고 성공"
                ));
    }

    //방명록 답글 신고
    @PostMapping("/reply")
    @Operation(summary = "방명록 답글 신고 서비스", description = "방명록 답글을 신고하는 서비스")
    @ApiResponse(responseCode = "201", description = "방명록 답글 신고 완료")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    public ResponseEntity<ApiResponseDTO> guestbookReplyReport(@RequestBody GuestbookReplyReportCreateRequestDTO guestbookReplyReportCreateRequestDTO) {

        guestbookReplyReportService.write(guestbookReplyReportCreateRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.of(
                        true,
                        "방명록 답글 신고 성공"
                ));
    }

    //방명록 대댓글 신고
    @PostMapping("/rereply")
    @Operation(summary = "방명록 대댓글 신고 서비스", description = "방명록 대댓글을 신고하는 서비스")
    @ApiResponse(responseCode = "201", description = "방명록 대댓글 신고 완료")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    public ResponseEntity<ApiResponseDTO> guestbookRereplyReport(@RequestBody GuestbookRereplyReportCreateRequestDTO guestbookRereplyReportCreateRequestDTO) {

        guestbookRereplyReportService.write(guestbookRereplyReportCreateRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.of(
                        true,
                        "방명록 대댓글 신고 성공"
                ));
    }
}
