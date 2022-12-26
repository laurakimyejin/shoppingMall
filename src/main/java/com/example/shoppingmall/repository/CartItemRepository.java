package com.example.shoppingmall.repository;

import com.example.shoppingmall.entity.CartEntity;
import com.example.shoppingmall.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItemEntity,Long> {
}
