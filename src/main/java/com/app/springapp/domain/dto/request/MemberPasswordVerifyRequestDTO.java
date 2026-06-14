package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "비밀번호 확인 요청 DTO")
public class MemberPasswordVerifyRequestDTO {

    @Schema(description = "현재 비밀번호", example = "currentPassword123!", required = true)
    private String password;
}
