package com.example.shoppingmall.dto;

import com.example.shoppingmall.entity.MemberEntity;
import com.example.shoppingmall.entity.QuestionEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class QuestionDTO {
    private Long id;
    private String questionName;
    private String questionTitle;
    private String questionContents;
    private String questionStatus = "N";
    private LocalDateTime questionCreatedTime;
    private LocalDateTime questionUpdatedTime;
    private Long memberId;



    public static QuestionDTO toDTO(QuestionEntity questionEntity) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(questionEntity.getId());
        questionDTO.setQuestionName(questionEntity.getQuestionName());
        questionDTO.setQuestionTitle(questionEntity.getQuestionTitle());
        questionDTO.setQuestionContents(questionEntity.getQuestionContents());
        questionDTO.setQuestionStatus(questionEntity.getQuestionStatus());
        questionDTO.setQuestionCreatedTime(questionEntity.getCreatedTime());
        questionDTO.setQuestionUpdatedTime(questionEntity.getUpdatedTime());
        return questionDTO;
    }

    public static <U> U toQuestionDTO(QuestionEntity questionEntity) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(questionEntity.getId());
        questionDTO.setQuestionName(questionEntity.getQuestionName());
        questionDTO.setQuestionTitle(questionEntity.getQuestionTitle());
        questionDTO.setQuestionContents(questionEntity.getQuestionContents());
        questionDTO.setQuestionStatus(questionEntity.getQuestionStatus());
        questionDTO.setQuestionCreatedTime(questionEntity.getCreatedTime());
        questionDTO.setQuestionUpdatedTime(questionEntity.getUpdatedTime());
        return (U) questionDTO;
    }
}
