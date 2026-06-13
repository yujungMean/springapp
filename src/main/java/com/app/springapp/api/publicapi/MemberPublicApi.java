package com.app.springapp.api.publicapi;

import com.app.springapp.domain.dto.MemberDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "회원 공개 API", description = "비로그인 접근 가능한 회원 기본 정보 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberPublicApi {

    private final MemberService memberService;

    @Operation(summary = "회원 공개 정보 조회", description = "다른 사람의 프로필 페이지에서 닉네임/프로필 이미지를 표시하는 데 사용.")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> getMemberPublicInfo(@PathVariable Long id) {
        com.app.springapp.domain.dto.response.MemberResponseDTO dto =
                (com.app.springapp.domain.dto.response.MemberResponseDTO) memberService.me(id).getData();
        Map<String, Object> pub = new HashMap<>();
        pub.put("memberNickname", dto.getMemberNickname());
        pub.put("memberProfileImageUrl", dto.getMemberPicture());
        return ResponseEntity.ok(ApiResponseDTO.of(true, "회원 공개 정보 조회 성공", pub));
    }

    @Operation(summary = "이메일 핸들로 회원 공개 정보 조회", description = "URL의 /my-page/{handle}/profile 등에서 회원 ID, 닉네임, 프로필 이미지를 조회하는 데 사용.")
    @GetMapping("/handle/{handle}")
    public ResponseEntity<ApiResponseDTO> getMemberByHandle(@PathVariable String handle) {
        return ResponseEntity.ok(memberService.getByHandle(handle));
    }

    @Operation(summary = "회원 ID로 이메일 핸들 조회", description = "작성자 프로필 이동 시 /my-page/{handle}/profile 링크를 만드는 데 사용.")
    @GetMapping("/{id}/handle")
    public ResponseEntity<ApiResponseDTO> getMemberHandle(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getHandle(id));
    }
}
