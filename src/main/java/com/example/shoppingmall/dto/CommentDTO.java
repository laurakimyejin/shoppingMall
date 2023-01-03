package com.example.shoppingmall.dto;

import com.example.shoppingmall.entity.CommentEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private String commentWriter;
    private String commentContents;
    private LocalDateTime commentCreatedDate;
    private int starCount;
    private Long memberId;
    private Long itemId;
    private Long orderId;


    public CommentDTO(Long id, String commentWriter, String commentContents, LocalDateTime commentCreatedDate, int starCount) {
        this.id = id;
        this.commentWriter = commentWriter;
        this.commentContents = commentContents;
        this.commentCreatedDate = commentCreatedDate;
        this.starCount = starCount;
    }

    public static CommentDTO toCommentDTO(CommentEntity commentEntity){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setStarCount(commentEntity.getStarCount());
        commentDTO.setCommentCreatedDate(commentEntity.getCreatedTime());
        commentDTO.setItemId(commentEntity.getItemEntity().getId());
//        commentDTO.setMemberId(commentEntity.getMemberEntity().getId());
        return commentDTO;
    }


}
