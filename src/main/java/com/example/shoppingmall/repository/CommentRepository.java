package com.example.shoppingmall.repository;

import com.example.shoppingmall.entity.CommentEntity;
import com.example.shoppingmall.entity.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {


    Page<CommentEntity> findByItemEntity(ItemEntity itemEntity, PageRequest id);
}
