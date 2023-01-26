package com.example.shoppingmall.dto;

import com.example.shoppingmall.entity.BaseEntity;
import com.example.shoppingmall.entity.OrderEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO extends BaseEntity {
    private Long id;
    private int orderCount;
    private String orderName;
    private String memberAddress;
    private String detailAddress;
    private String extraAddress;
    private String postcode;
    private String memberMobile;
    private String orderStatus = "주문완료";
    private int orderPrice;
    private LocalDateTime orderCreatedTime;

    private String userId;
    private Long itemId;
    private Long memberId;
    private String memberName;
    private String review = "리뷰작성";


    public static <U> U toOrderDTO(OrderEntity orderEntity) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(orderEntity.getId());
        orderDTO.setUserId(orderEntity.getMemberEntity().getUserId());
        orderDTO.setOrderCount(orderEntity.getOrderItemEntityList().get(0).getOrderCount());
        orderDTO.setOrderName(orderEntity.getOrderItemEntityList().get(0).getOrderName());
        orderDTO.setMemberAddress(orderEntity.getMemberEntity().getMemberAddress());
        orderDTO.setDetailAddress(orderEntity.getMemberEntity().getDetailAddress());
        orderDTO.setExtraAddress(orderEntity.getMemberEntity().getExtraAddress());
        orderDTO.setMemberMobile(orderEntity.getMemberEntity().getMemberMobile());
        orderDTO.setOrderStatus(orderEntity.getOrderStatus());
        orderDTO.setItemId(orderEntity.getOrderItemEntityList().get(0).getItemEntity().getId());
        orderDTO.setMemberId(orderEntity.getMemberEntity().getId());
        orderDTO.setOrderPrice(orderEntity.getOrderItemEntityList().get(0).getOrderPrice());
        orderDTO.setMemberName(orderEntity.getMemberEntity().getMemberName());
        orderDTO.setPostcode(orderEntity.getMemberEntity().getPostcode());
        orderDTO.setOrderCreatedTime(orderEntity.getCreatedTime());
        return (U) orderDTO;
    }
}
