package com.example.shoppingmall.repository;

import com.example.shoppingmall.entity.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity,Long> {
    Optional<ItemEntity> findByItemName(String orderName);

    Page<ItemEntity> findByItemNameContaining(String search, PageRequest of);
}
