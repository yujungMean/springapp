package com.app.springapp.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

//CREATE SEQUENCE SEQ_MEMBER START WITH 1 INCREMENT BY 1 NOCACHE;
//CREATE TABLE TBL_MEMBER (
//        ID                          NUMBER          CONSTRAINT PK_MEMBER PRIMARY KEY,
//        MEMBER_EMAIL                VARCHAR2(255)   NOT NULL,
//MEMBER_PASSWORD             VARCHAR2(255)   NULL,
//MEMBER_NAME                 VARCHAR2(255)   NOT NULL,
//MEMBER_PHONE                VARCHAR2(255)   NOT NULL,
//MEMBER_NICKNAME             VARCHAR2(255)   NOT NULL,
//MEMBER_CREATE_AT            TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
//MEMBER_PROFILE_IMAGE_URL    VARCHAR2(255)   NULL,
//MEMBER_EMAIL_VERIFIED_AT    TIMESTAMP       NULL,
//MEMBER_PHONE_VERIFIED_AT    TIMESTAMP       NULL
//);

@Component
@Data
public class MemberVO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPhone;
    private String memberNickname;
    private String memberCreateAt;
    private String memberProfileImageUrl;
    private String memberEmailVerifiedAt;
    private String memberPhoneVerifiedAt;
}
