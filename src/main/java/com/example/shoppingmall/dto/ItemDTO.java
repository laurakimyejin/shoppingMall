package com.example.shoppingmall.dto;

import com.example.shoppingmall.entity.ItemEntity;
import com.example.shoppingmall.entity.ItemFileEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private int itemSellCount;
    private String itemCategory;
//    private List<String> itemImage;
    private int fileAttachedItem;

    private List<MultipartFile> itemFile;
    private List<String> originalFileNameItem;
    private List<String> storedFileNameItem;
    private String itemImage;
    private String userId;
    private int cartCount;

    public static ItemDTO toItemDTO(ItemEntity itemEntity){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(itemEntity.getId());
        itemDTO.setItemName(itemEntity.getItemName());
        itemDTO.setItemPrice(itemEntity.getItemPrice());
        itemDTO.setItemContents(itemEntity.getItemContents());
        itemDTO.setItemCreatedDate(itemEntity.getCreatedTime());
        itemDTO.setItemCount(itemEntity.getItemCount());
        itemDTO.setItemCategory(itemEntity.getItemCategory());
//        itemDTO.setItemImage(itemEntity.getItemImage());
        if(itemEntity.getFileAttachedItem()==1){
            itemDTO.setFileAttachedItem(itemEntity.getFileAttachedItem());
            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();
            for(ItemFileEntity itemFileEntity : itemEntity.getItemFileEntityList()){
                originalFileNameList.add(itemFileEntity.getOriginalFileNameItem());
                storedFileNameList.add(itemFileEntity.getStoredFileNameItem());
            }
            itemDTO.setOriginalFileNameItem(originalFileNameList);
            itemDTO.setStoredFileNameItem(storedFileNameList);
//            itemDTO.setItemImage(itemDTO.getStoredFileNameItem());
        }else{
            itemDTO.setFileAttachedItem(itemEntity.getFileAttachedItem());
        }
        return itemDTO;
    }
}
