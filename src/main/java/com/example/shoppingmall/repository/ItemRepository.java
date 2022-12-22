package com.example.shoppingmall.repository;

import com.example.shoppingmall.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity,Long> {
}
