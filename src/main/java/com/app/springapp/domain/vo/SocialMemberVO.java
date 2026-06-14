package com.app.springapp.domain.vo;

import com.app.springapp.domain.dto.MemberDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SocialMemberVO {
    private Long id;
    private String socialMemberProviderId;
    private String socialMemberProvider;
    private Long memberId;
    private String socialAccessToken;
    private String socialRefreshToken;

    public static SocialMemberVO from(MemberDTO memberJoinDTO){
        SocialMemberVO socialMemberVO = new SocialMemberVO();
        socialMemberVO.setId(memberJoinDTO.getId());
        socialMemberVO.setSocialMemberProviderId(memberJoinDTO.getSocialMemberProviderId());
        socialMemberVO.setSocialMemberProvider(memberJoinDTO.getSocialMemberProvider() != null ? memberJoinDTO.getSocialMemberProvider() : "local");
        socialMemberVO.setMemberId(memberJoinDTO.getMemberId());
        socialMemberVO.setSocialAccessToken(memberJoinDTO.getSocialAccessToken());
        socialMemberVO.setSocialRefreshToken(memberJoinDTO.getSocialRefreshToken());
        return socialMemberVO;
    }
}
