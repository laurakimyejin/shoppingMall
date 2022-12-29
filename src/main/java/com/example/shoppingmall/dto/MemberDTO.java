package com.example.shoppingmall.dto;

import com.example.shoppingmall.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDTO {
    private Long id;
    private String userId;
    private String memberPassword;
    private String memberEmail;
    private String memberName;
    private String memberMobile;
    private String memberAddress;
    private LocalDateTime memberCreatedTime;
    private LocalDateTime memberUpdatedTime;

    private String memberPasswordUpdate;

    public MemberDTO(Long id, String userId, String memberEmail, String memberName, String memberMobile, LocalDateTime memberCreatedTime) {
        this.id = id;
        this.userId = userId;
        this.memberEmail = memberEmail;
        this.memberName = memberName;
        this.memberMobile = memberMobile;
        this.memberCreatedTime = memberCreatedTime;
    }

    public static MemberDTO toDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setUserId(memberEntity.getUserId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberMobile(memberEntity.getMemberMobile());
        memberDTO.setMemberAddress(memberEntity.getMemberAddress());
        memberDTO.setMemberCreatedTime(memberEntity.getCreatedTime());
        memberDTO.setMemberUpdatedTime(memberEntity.getUpdatedTime());
        return memberDTO;
    }
}
