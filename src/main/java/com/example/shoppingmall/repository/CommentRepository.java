package com.example.shoppingmall.repository;

import com.example.shoppingmall.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
}
