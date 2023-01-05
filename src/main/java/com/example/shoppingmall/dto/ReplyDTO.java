package com.example.shoppingmall.dto;

import com.example.shoppingmall.entity.ReplyEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReplyDTO {
    private Long id;
    private String replyContents;
    private String replyName;
    private LocalDateTime replyDate;
    private Long questionId;
    private Long memberId;


    public static ReplyDTO toReplyDTO(ReplyEntity replyEntity) {
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setId(replyEntity.getId());
        replyDTO.setReplyContents(replyEntity.getReplyContents());
        replyDTO.setReplyName(replyEntity.getReplyName());
        replyDTO.setReplyDate(replyEntity.getCreatedTime());
        return replyDTO;
    }
}
