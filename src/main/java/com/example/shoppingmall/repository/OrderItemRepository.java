package com.example.shoppingmall.repository;
import com.example.shoppingmall.entity.OrderEntity;
import com.example.shoppingmall.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

    OrderItemEntity findByOrderEntity(OrderEntity orderEntity);
}

