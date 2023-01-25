package com.example.shoppingmall.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor

public class CartItemDTO {
    private Long id;
    private int cartCount;

    private String itemName;
    private int itemPrice;
    private int itemCount;
    private String itemImage;
    private int itemPriceTotal;

}
