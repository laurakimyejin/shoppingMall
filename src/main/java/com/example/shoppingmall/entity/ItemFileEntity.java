package com.example.shoppingmall.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "item_file_table")
public class ItemFileEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String originalFileNameItem;

    @Column(length = 100, nullable = false)
    private String storedFileNameItem;

    //상품-상품이미지 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity itemEntity;

    public static ItemFileEntity toSaveItemFileEntity(ItemEntity entity, String originalFileNameItem, String storedFileNameItem){
        ItemFileEntity itemFileEntity = new ItemFileEntity();
        itemFileEntity.setOriginalFileNameItem(originalFileNameItem);
        itemFileEntity.setStoredFileNameItem(storedFileNameItem);
        itemFileEntity.setItemEntity(entity);
        return itemFileEntity;
    }

}
