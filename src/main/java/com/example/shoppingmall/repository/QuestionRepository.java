package com.example.shoppingmall.repository;

import com.example.shoppingmall.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionEntity,Long> {
}
