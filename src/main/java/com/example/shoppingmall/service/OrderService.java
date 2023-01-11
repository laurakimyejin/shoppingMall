package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.CartItemDTO;
import com.example.shoppingmall.dto.OrderDTO;
import com.example.shoppingmall.entity.*;
import com.example.shoppingmall.repository.*;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public void save(OrderDTO orderDTO) {
        Optional<MemberEntity> memberEntity1 = memberRepository.findById(orderDTO.getMemberId());
        ItemEntity itemEntity1 = itemRepository.findById(orderDTO.getItemId()).get();
        itemEntity1.setItemSellCount(itemEntity1.getItemSellCount() + orderDTO.getOrderCount());
        itemRepository.save(itemEntity1);
        if (memberEntity1.isPresent()) {
            MemberEntity memberEntity2 = memberEntity1.get();
            memberEntity2.setMemberAddress(orderDTO.getMemberAddress());
            memberEntity2.setDetailAddress(orderDTO.getDetailAddress());
            memberEntity2.setExtraAddress(orderDTO.getExtraAddress());
            memberEntity2.setPostcode(orderDTO.getPostcode());
            memberRepository.save(memberEntity2);
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderStatus(orderDTO.getOrderStatus());
            orderEntity.setReview(orderDTO.getReview());
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
        List<OrderEntity> orderEntityList = orderRepository.findByMemberEntity(memberEntity);
        System.out.println("orderEntityList = " + orderEntityList);
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntityList) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(orderEntity.getId());
            orderDTO.setReview(orderEntity.getReview());
            orderDTO.setOrderStatus(orderEntity.getOrderStatus());
            orderDTO.setOrderName(orderEntity.getOrderName());
            orderDTOList.add(orderDTO);

        }
        return orderDTOList;
    }


    @Transactional
    public Page<OrderDTO> findListAll(Pageable pageable, String sort) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = pageable.getPageSize();
        Page<OrderEntity> orderEntityList = orderRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, sort)));
        Page<OrderDTO> orderDTOList = orderEntityList.map(OrderDTO::toOderDTO);
        return orderDTOList;
    }

    @Transactional
    public List<CartItemDTO> findCartById(String userId) {
        MemberEntity memberEntity = memberRepository.findByUserId(userId).get();
        CartEntity cartEntity = cartRepository.findByMemberEntity(memberEntity).get();
        List<CartItemEntity> cartEntityList = cartItemRepository.findByCartEntity(cartEntity);
        System.out.println("cartEntityList = " + cartEntityList);
        List<CartItemDTO> cartItemDTOList = new ArrayList<>();
        for (CartItemEntity cartItemEntity : cartEntityList) {
            CartItemDTO cartItemDTO = new CartItemDTO();
            cartItemDTO.setId(cartItemEntity.getId());
            cartItemDTO.setItemName(cartItemEntity.getItemEntity().getItemName());
            cartItemDTO.setItemPrice(cartItemEntity.getItemEntity().getItemPrice());
            cartItemDTO.setItemCount(cartItemEntity.getItemEntity().getItemCount());
            cartItemDTO.setCartCount(cartItemEntity.getCartCount());
            cartItemDTO.setItemImage(cartItemEntity.getItemEntity().getItemFileEntityList().get(0).getStoredFileNameItem());
            cartItemDTOList.add(cartItemDTO);
        }
        return cartItemDTOList;
    }

    @Transactional
    public void save2(JSONArray itemDTOList, String userId) throws JSONException {
        MemberEntity memberEntity = memberRepository.findByUserId(userId).get();
        memberEntity.setPostcode(itemDTOList.getJSONObject(0).getString("postcode"));
        memberEntity.setMemberAddress(itemDTOList.getJSONObject(0).getString("memberAddress"));
        memberEntity.setDetailAddress(itemDTOList.getJSONObject(0).getString("detailAddress"));
        memberEntity.setExtraAddress(itemDTOList.getJSONObject(0).getString("extraAddress"));
        memberRepository.save(memberEntity);
        for (int i = 0; i < itemDTOList.length(); i++) {
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            OrderEntity orderEntity = new OrderEntity();
            ItemEntity itemEntity = itemRepository.findByItemName(itemDTOList.getJSONObject(i).getString("itemName")).get();
            itemEntity.setItemSellCount(itemEntity.getItemSellCount() + itemDTOList.getJSONObject(i).getInt("cartCount"));
            orderEntity.setOrderStatus("주문완료");
            orderEntity.setReview("리뷰작성");
            orderEntity.setMemberEntity(memberEntity);
            orderEntity.setOrderName(itemDTOList.getJSONObject(i).getString("itemName"));
            orderItemEntity.setOrderName(itemDTOList.getJSONObject(i).getString("itemName"));
            orderItemEntity.setOrderPrice(itemDTOList.getJSONObject(i).getInt("itemPrice"));
            orderItemEntity.setOrderCount(itemDTOList.getJSONObject(i).getInt("cartCount"));
            orderEntity=orderRepository.save(orderEntity);
            orderItemEntity.setOrderEntity(orderEntity);
            orderItemEntity.setItemEntity(itemEntity);
            orderItemRepository.save(orderItemEntity);
        }
        for (int i = 0; i < itemDTOList.length(); i++) {
            CartItemEntity cartItemEntity = cartItemRepository.findById(itemDTOList.getJSONObject(i).getLong("id")).get();
            cartItemRepository.delete(cartItemEntity);
        }

    }

    public void update(Long id, String status) {
        OrderEntity orderEntity=orderRepository.findById(id).get();
        orderEntity.setOrderStatus(status);
        orderRepository.save(orderEntity);
    }
}

