package com.example.shoppingmall.repository;

import com.example.shoppingmall.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
}
