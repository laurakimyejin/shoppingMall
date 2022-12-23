package com.example.shoppingmall.dto;

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

}
