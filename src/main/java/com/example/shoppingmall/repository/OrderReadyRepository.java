package com.example.shoppingmall.repository;

import com.example.shoppingmall.entity.MemberEntity;
import com.example.shoppingmall.entity.OrderReadyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderReadyRepository extends JpaRepository<OrderReadyEntity, Long> {

    List<OrderReadyEntity> findByMemberEntity(MemberEntity memberEntity);
}

