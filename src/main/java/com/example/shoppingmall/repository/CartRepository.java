package com.example.shoppingmall.repository;

import com.example.shoppingmall.entity.CartEntity;
import com.example.shoppingmall.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity,Long> {
    Optional<CartEntity> findByMemberEntity(MemberEntity memberEntity);

}
