package com.example.shoppingmall.repository;

import com.example.shoppingmall.entity.MemberEntity;
import com.example.shoppingmall.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {


    List<OrderEntity> findByMemberEntity(MemberEntity memberEntity);


    void deleteByMemberEntity(MemberEntity memberEntity2);
}
