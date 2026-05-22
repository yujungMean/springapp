package com.app.springapp.domain.dto.response;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class GuestbookRereplyResponseDTO {

    private Long id;
    private String guestbookRereplyContent;
    private String guestbookRereplyCreatedAt;
    private Long likeCount;
    private Long guestbookReplyId;
    private Long writerMemberId;
    private String writerNickname;
    private String writerProfileImageUrl;

}
