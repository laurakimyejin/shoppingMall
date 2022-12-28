package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.MemberDTO;
import com.example.shoppingmall.dto.OrderDTO;
import com.example.shoppingmall.entity.ItemEntity;
import com.example.shoppingmall.entity.MemberEntity;
import com.example.shoppingmall.entity.OrderEntity;
import com.example.shoppingmall.entity.OrderItemEntity;
import com.example.shoppingmall.repository.ItemRepository;
import com.example.shoppingmall.repository.MemberRepository;
import com.example.shoppingmall.repository.OrderItemRepository;
import com.example.shoppingmall.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;

    public void save(OrderDTO orderDTO) {
        Optional<MemberEntity> memberEntity1 = memberRepository.findById(orderDTO.getMemberId());
        ItemEntity itemEntity1 = itemRepository.findById(orderDTO.getItemId()).get();
        if (memberEntity1.isPresent()) {
            MemberEntity memberEntity2 = memberEntity1.get();
            memberEntity2.setMemberAddress(orderDTO.getMemberAddress());
            memberRepository.save(memberEntity2);
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderStatus(orderDTO.getOrderStatus());
            orderEntity.setMemberEntity(memberEntity2);
            orderRepository.save(orderEntity);
            OrderItemEntity itemEntity = new OrderItemEntity();
            itemEntity.setOrderEntity(orderEntity);
            itemEntity.setItemEntity(itemEntity1);
            itemEntity.setOrderCount(orderDTO.getOrderCount());
            itemEntity.setOrderName(orderDTO.getOrderName());
            itemEntity.setOrderPrice(orderDTO.getOrderPrice());
            orderItemRepository.save(itemEntity);
        }

    }
}
