package com.example.shoppingmall.repository;

import com.example.shoppingmall.entity.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    EntityManager entityManager = null;

    Optional<ItemEntity> findByItemName(String orderName);

    Page<ItemEntity> findByItemNameContaining(String search, PageRequest of);

    Page<ItemEntity> findByItemCategory(String small, PageRequest of);

    @Query("SELECT i FROM ItemEntity i WHERE i.itemCategory = :category AND i.itemName LIKE %:search%")
    Page<ItemEntity> findByItemCategoryAndItemNameLike(@Param("category") String category, @Param("search") String search, Pageable pageable);

    Page<ItemEntity> findByItemNameContainingAndItemCategory(String search, String small, PageRequest of);
}



