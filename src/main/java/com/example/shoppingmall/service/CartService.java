package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.CartDTO;
import com.example.shoppingmall.dto.ItemDTO;
import com.example.shoppingmall.entity.CartEntity;
import com.example.shoppingmall.entity.CartItemEntity;
import com.example.shoppingmall.entity.ItemEntity;
import com.example.shoppingmall.repository.CartItemRepository;
import com.example.shoppingmall.repository.CartRepository;
import com.example.shoppingmall.repository.ItemRepository;
import com.example.shoppingmall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;
    public void save(ItemDTO itemDTO){
        CartEntity cartEntity = new CartEntity();
        memberRepository.findByUserId(itemDTO.getUserId()).ifPresent(memberEntity -> {
            cartEntity.setMemberEntity(memberEntity);
            cartRepository.save(cartEntity);
            CartItemEntity cartItemEntity = new CartItemEntity();
            cartItemEntity.setCardName(itemDTO.getItemName());
            cartItemEntity.setCardCount(itemDTO.getItemCount());
            cartItemEntity.setCartEntity(cartEntity);
            cartItemEntity.setCardCount(itemDTO.getCartCount());
            ItemEntity itemEntity =itemRepository.findById(itemDTO.getId()).get();
            cartItemEntity.setItemEntity(itemEntity);
            cartItemRepository.save(cartItemEntity);
        });




    }
}
