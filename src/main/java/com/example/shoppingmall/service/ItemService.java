package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.ItemDTO;
import com.example.shoppingmall.entity.ItemEntity;
import com.example.shoppingmall.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Long save(ItemDTO itemDTO) {
       ItemEntity itemEntity = ItemEntity.toItemSaveEntity(itemDTO);
       return itemRepository.save(itemEntity).getId();

    }
}
