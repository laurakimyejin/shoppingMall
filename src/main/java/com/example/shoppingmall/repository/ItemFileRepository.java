package com.example.shoppingmall.repository;

import com.example.shoppingmall.entity.ItemEntity;
import com.example.shoppingmall.entity.ItemFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemFileRepository extends JpaRepository<ItemFileEntity,Long> {

    List<ItemEntity> findByItemEntity(ItemEntity itemEntity2);

    void deleteByItemEntity(ItemEntity itemEntity2);
}
