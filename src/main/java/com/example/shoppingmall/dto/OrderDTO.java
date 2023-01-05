package com.example.shoppingmall.dto;

import com.example.shoppingmall.entity.OrderEntity;
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
    private String detailAddress;
    private String extraAddress;
    private String postcode;
    private String memberMobile;
    private String orderStatus = "배송준비";
    private Long itemId;
    private Long memberId;
    private String memberName;
    private int orderPrice;
    private String review = "후기작성";

    public static <U> U toOderDTO(OrderEntity orderEntity) {
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
        return (U) orderDTO;
    }
}
