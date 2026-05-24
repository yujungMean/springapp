package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "게시글 작성 응답 dto")
public class PostCreateResponseDTO {

    @Schema(description = "작성된 게시글 id", example = "1")
    private Long postId;
}
