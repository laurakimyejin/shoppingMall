package com.example.shoppingmall.dto;

import com.example.shoppingmall.entity.ItemEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ItemDTO {
    private Long id;
    private String itemName;
    private int itemPrice;
    private String itemContents;
    private LocalDateTime itemCreatedDate;
    private int itemCount;
    private String itemCategory;
    private String itemImage;
    private int fileAttachedItem;

    public static ItemDTO toItemDTO(ItemEntity itemEntity){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(itemEntity.getId());
        itemDTO.setItemName(itemEntity.getItemName());
        itemDTO.setItemPrice(itemEntity.getItemPrice());
        itemDTO.setItemContents(itemEntity.getItemContents());
        itemDTO.setItemCount(itemEntity.getItemCount());
        itemDTO.setItemCategory(itemEntity.getItemCategory());
        itemDTO.setItemImage(itemEntity.getItemImage());
        return itemDTO;
    }

}
