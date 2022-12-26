package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.CartDTO;
import com.example.shoppingmall.dto.ItemDTO;
import com.example.shoppingmall.entity.CartEntity;
import com.example.shoppingmall.entity.CartItemEntity;
import com.example.shoppingmall.entity.ItemEntity;
import com.example.shoppingmall.entity.MemberEntity;
import com.example.shoppingmall.repository.CartItemRepository;
import com.example.shoppingmall.repository.CartRepository;
import com.example.shoppingmall.repository.ItemRepository;
import com.example.shoppingmall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;

    public void save(ItemDTO itemDTO) {
        MemberEntity memberEntity = memberRepository.findByUserId(itemDTO.getUserId()).get();
        Optional<CartEntity>cartEntity = cartRepository.findByMemberEntity(memberEntity);
        if (cartEntity.isPresent()) {
            CartEntity cartEntity1 = cartEntity.get();
            cartEntity1.setMemberEntity(memberEntity);
            cartEntity1.setId(cartEntity1.getId());
            cartRepository.save(cartEntity1);
            CartItemEntity cartItemEntity = new CartItemEntity();
            cartItemEntity.setCardName(itemDTO.getItemName());
            cartItemEntity.setCardCount(itemDTO.getItemCount());
            cartItemEntity.setCartEntity(cartEntity1);
            cartItemEntity.setCardCount(itemDTO.getCartCount());
            ItemEntity itemEntity =itemRepository.findById(itemDTO.getId()).get();
            cartItemEntity.setItemEntity(itemEntity);
            cartItemRepository.save(cartItemEntity);

        }else {
            CartEntity cartEntity1 = new CartEntity();
            cartEntity1.setMemberEntity(memberEntity);
            cartRepository.save(cartEntity1);
            CartItemEntity cartItemEntity = new CartItemEntity();
            cartItemEntity.setCardName(itemDTO.getItemName());
            cartItemEntity.setCardCount(itemDTO.getItemCount());
            cartItemEntity.setCartEntity(cartEntity1);
            cartItemEntity.setCardCount(itemDTO.getCartCount());
            ItemEntity itemEntity =itemRepository.findById(itemDTO.getId()).get();
            cartItemEntity.setItemEntity(itemEntity);
            cartItemRepository.save(cartItemEntity);
        }
    }


}
