package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "방명록 신고 요청 DTO")
public class GuestbookReportCreateRequestDTO {

    @Schema(description = "신고자", example = "1", required = true)
    private Long memberId;

    @Schema(description = "신고할 방명록 ID", example = "1", required = true)
    private Long guestbookId;

    @Schema(description = "신고 사유 카테고리 ID", example = "3", required = true)
    private Long reportReasonCategoryId;

    @Schema(description = "신고 내용", example = "욕설이 포함된 방명록입니다.", required = true)
    private String guestbookReportContent;
}
