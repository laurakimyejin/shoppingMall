package com.example.shoppingmall.repository;

import com.example.shoppingmall.dto.ReplyDTO;
import com.example.shoppingmall.entity.QuestionEntity;
import com.example.shoppingmall.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<ReplyEntity,Long> {
    List<ReplyEntity> findByQuestionEntity(QuestionEntity question);

}
