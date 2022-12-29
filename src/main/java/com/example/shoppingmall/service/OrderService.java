package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.CartItemDTO;
import com.example.shoppingmall.dto.MemberDTO;
import com.example.shoppingmall.dto.OrderDTO;
import com.example.shoppingmall.entity.*;
import com.example.shoppingmall.repository.ItemRepository;
import com.example.shoppingmall.repository.MemberRepository;
import com.example.shoppingmall.repository.OrderItemRepository;
import com.example.shoppingmall.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
        itemEntity1.setItemSellCount(itemEntity1.getItemSellCount() + orderDTO.getOrderCount());
        itemRepository.save(itemEntity1);
        if (memberEntity1.isPresent()) {
            MemberEntity memberEntity2 = memberEntity1.get();
            memberEntity2.setMemberAddress(orderDTO.getMemberAddress());
            memberRepository.save(memberEntity2);
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderStatus(orderDTO.getOrderStatus());
            orderEntity.setMemberEntity(memberEntity2);
            orderEntity.setOrderName(orderDTO.getOrderName());
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

    @Transactional
    public List<OrderDTO> findAll(String userId) {
        MemberEntity memberEntity = memberRepository.findByUserId(userId).get();
        List<OrderEntity>orderEntityList = orderRepository.findByMemberEntity(memberEntity);
        System.out.println("orderEntityList = " + orderEntityList);
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntityList) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(orderEntity.getId());
            orderDTO.setOrderStatus(orderEntity.getOrderStatus());
            orderDTO.setOrderName(orderEntity.getOrderName());
            orderDTOList.add(orderDTO);

        }
        return orderDTOList;
    }





}
