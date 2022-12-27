package com.example.shoppingmall.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {
    private int startPage;
    private int endPage;
    private Long itemId;
}
