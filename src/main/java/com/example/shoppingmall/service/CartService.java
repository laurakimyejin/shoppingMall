package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.CartDTO;
import com.example.shoppingmall.dto.CartItemDTO;
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
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public void save(ItemDTO itemDTO) {
        MemberEntity memberEntity = memberRepository.findByUserId(itemDTO.getUserId()).get();
        Optional<CartEntity>cartEntity = cartRepository.findByMemberEntity(memberEntity);
        if (cartEntity.isPresent()) {
            CartEntity cartEntity1 = cartEntity.get();
            cartEntity1.setMemberEntity(memberEntity);
            cartEntity1.setId(cartEntity1.getId());
            cartRepository.save(cartEntity1);

            CartItemEntity cartItemEntity = new CartItemEntity();
            cartItemEntity.setCartName(itemDTO.getItemName());
            cartItemEntity.setCartCount(itemDTO.getItemCount());
            cartItemEntity.setCartCount(itemDTO.getCartCount());
            cartItemEntity.setCartEntity(cartEntity1);

            ItemEntity itemEntity =itemRepository.findById(itemDTO.getId()).get();
            cartItemEntity.setItemEntity(itemEntity);

            cartItemRepository.save(cartItemEntity);

        }else {
            CartEntity cartEntity1 = new CartEntity();
            cartEntity1.setMemberEntity(memberEntity);
            cartRepository.save(cartEntity1);

            CartItemEntity cartItemEntity = new CartItemEntity();
            cartItemEntity.setCartName(itemDTO.getItemName());
            cartItemEntity.setCartCount(itemDTO.getItemCount());
            cartItemEntity.setCartCount(itemDTO.getCartCount());
            cartItemEntity.setCartEntity(cartEntity1);

            ItemEntity itemEntity =itemRepository.findById(itemDTO.getId()).get();
            cartItemEntity.setItemEntity(itemEntity);

            cartItemRepository.save(cartItemEntity);
        }
    }


    @Transactional
    public List<CartItemDTO> findAll(String userId) {
        MemberEntity memberEntity = memberRepository.findByUserId(userId).get();
        Optional<CartEntity>cartEntity = cartRepository.findByMemberEntity(memberEntity);
        if (cartEntity.isPresent()) {
            CartEntity cartEntity1 = cartEntity.get();
            List<CartItemEntity>cartItemEntityList = cartItemRepository.findByCartEntity(cartEntity1);
            List<CartItemDTO>cartItemDTOList = new ArrayList<>();
            for (CartItemEntity cartItemEntity : cartItemEntityList) {
                CartItemDTO cartItemDTO = new CartItemDTO();
                cartItemDTO.setId(cartItemEntity.getId());
                cartItemDTO.setCartCount(cartItemEntity.getCartCount());
                cartItemDTO.setItemName(cartItemEntity.getItemEntity().getItemName());
                cartItemDTO.setItemPrice(cartItemEntity.getItemEntity().getItemPrice());
                cartItemDTO.setItemImage(cartItemEntity.getItemEntity().getItemFileEntityList().get(0).getStoredFileNameItem());
                cartItemDTOList.add(cartItemDTO);
            }
            return cartItemDTOList;

        }else {
            return null;
        }
    }
    @Transactional
    public CartItemDTO findById(Long id) {
        Optional<CartItemEntity> cartEntityOptional = cartItemRepository.findById(id);
        if(cartEntityOptional.isPresent()){
            CartItemEntity cartEntity = cartEntityOptional.get();
            CartItemDTO cartItemDTO = new CartItemDTO();
            cartItemDTO.setId(cartEntity.getId());
            cartItemDTO.setCartCount(cartEntity.getCartCount());
            cartItemDTO.setItemName(cartEntity.getItemEntity().getItemName());
            cartItemDTO.setItemPrice(cartEntity.getItemEntity().getItemPrice());
            cartItemDTO.setItemImage(cartEntity.getItemEntity().getItemFileEntityList().get(0).getStoredFileNameItem());
            return cartItemDTO;
        }else{
            return null;
        }
    }
    @Transactional
    public void delete(Long id) {
        cartItemRepository.deleteById(id);
    }
    @Transactional
    public void update(ItemDTO itemDTO) {
        MemberEntity memberEntity = memberRepository.findByUserId(itemDTO.getUserId()).get();
        Optional<CartEntity>cartEntity = cartRepository.findByMemberEntity(memberEntity);

        CartEntity cartEntity1 = cartEntity.get();
        cartEntity1.setMemberEntity(memberEntity);
        cartEntity1.setId(cartEntity1.getId());
        cartRepository.save(cartEntity1);

        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setId(itemDTO.getId());
        cartItemEntity.setCartName(itemDTO.getItemName());
        cartItemEntity.setCartCount(itemDTO.getItemCount());
        cartItemEntity.setCartCount(itemDTO.getCartCount());
        cartItemEntity.setCartEntity(cartEntity1);

        ItemEntity itemEntity =itemRepository.findById(itemDTO.getId()).get();
        cartItemEntity.setItemEntity(itemEntity);
        cartItemRepository.save(cartItemEntity);
    }
}
