package com.example.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartDTO {
    private Long id;
    private String userId;
    private int cartCount;

}
