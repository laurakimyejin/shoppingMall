package com.example.shoppingmall.entity;

import com.example.shoppingmall.dto.CommentDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "comment_table")
public class CommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String commentWriter;

    @Column(length = 500)
    private String commentContents;

    @Column(nullable = false)
    private int starCount;

    //상품-후기 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity itemEntity;

    public static CommentEntity toCommentEntity(ItemEntity itemEntity, CommentDTO commentDTO){
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setItemEntity(itemEntity);
//        commentEntity.setMemberEntity(memberEntity);
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        commentEntity.setStarCount(commentDTO.getStarCount());
        return commentEntity;

    }
}
