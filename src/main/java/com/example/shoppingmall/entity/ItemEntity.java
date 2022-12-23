package com.example.shoppingmall.entity;

import com.example.shoppingmall.dto.ItemDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "item_table")
public class ItemEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String itemName;

    @Column(nullable = false)
    private int itemPrice;

    @Column(length = 500)
    private String itemContents;

    @Column
    private int itemCount = 0;

    @Column(length = 100)
    private String itemImage;

    @Column
    private int fileAttachedItem;

    @Column(length = 30)
    private String itemCategory;

    //item(상품) : item_file(상품이미지) = 1 : M
    @OneToMany(mappedBy = "itemEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemFileEntity> itemFileEntityList = new ArrayList<>();


    public static ItemEntity toItemSaveEntity(ItemDTO itemDTO) {
        ItemEntity itementity = new ItemEntity();
        itementity.setItemName(itemDTO.getItemName());
        itementity.setItemPrice(itemDTO.getItemPrice());
        itementity.setItemContents(itemDTO.getItemContents());
        itementity.setItemCount(itemDTO.getItemCount());
        itementity.setItemImage(itemDTO.getItemImage());
        itementity.setItemCategory(itemDTO.getItemCategory());
        itementity.setFileAttachedItem(0);
        return itementity;
    }

    public static ItemEntity toItemSaveFileEntity(ItemDTO itemDTO) {
        ItemEntity itementity = new ItemEntity();
        itementity.setItemName(itemDTO.getItemName());
        itementity.setItemPrice(itemDTO.getItemPrice());
        itementity.setItemContents(itemDTO.getItemContents());
        itementity.setItemCount(itemDTO.getItemCount());
        itementity.setItemImage(itemDTO.getItemImage());
        itementity.setItemCategory(itemDTO.getItemCategory());
        itementity.setFileAttachedItem(1);
        return itementity;
    }
}