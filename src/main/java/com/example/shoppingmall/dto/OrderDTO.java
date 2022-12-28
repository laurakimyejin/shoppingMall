package com.example.shoppingmall.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private String userId;
    private int orderCount;
    private String orderName;
    private String memberAddress;
    private String memberMobile;
    private String orderStatus = "배송준비";
    private Long itemId;
    private Long memberId;
    private int orderPrice;
}
