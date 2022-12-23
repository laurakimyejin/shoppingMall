package com.example.shoppingmall.entity;

import com.example.shoppingmall.dto.QuestionDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "question_table")
public class QuestionEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String questionName;

    @Column(length = 50, nullable = false)
    private String questionTitle;

    @Column(length = 500)
    private String questionContents;

    @Column(length = 4, nullable = false)
    private String questionStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    public static QuestionEntity toSaveEntity(QuestionDTO questionDTO , MemberEntity memberEntity){
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestionName(questionDTO.getQuestionName());
        questionEntity.setQuestionTitle(questionDTO.getQuestionTitle());
        questionEntity.setQuestionContents(questionDTO.getQuestionContents());
        questionEntity.setQuestionStatus(questionDTO.getQuestionStatus());
        questionEntity.setMemberEntity(memberEntity);
        return questionEntity;
    }


}
