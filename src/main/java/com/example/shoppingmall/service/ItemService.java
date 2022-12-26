package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.CommentDTO;
import com.example.shoppingmall.dto.ItemDTO;
import com.example.shoppingmall.entity.CommentEntity;
import com.example.shoppingmall.entity.ItemEntity;
import com.example.shoppingmall.entity.ItemFileEntity;
import com.example.shoppingmall.repository.CommentRepository;
import com.example.shoppingmall.repository.ItemFileRepository;
import com.example.shoppingmall.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemFileRepository itemFileRepository;
    private final CommentRepository commentRepository;

    public Long save(ItemDTO itemDTO) throws IOException {
        if(itemDTO.getItemFile().get(0).isEmpty()){
            System.out.println("파일 없음");
           ItemEntity itemEntity = ItemEntity.toItemSaveEntity(itemDTO);
           return itemRepository.save(itemEntity).getId();
        }else{
            System.out.println("파일 있음");
            ItemEntity itemEntity = ItemEntity.toItemSaveFileEntity(itemDTO);
            Long savedId = itemRepository.save(itemEntity).getId();
            ItemEntity entity = itemRepository.findById(savedId).get();
            for(MultipartFile itemFile: itemDTO.getItemFile()){
                String originalFileNameItem = itemFile.getOriginalFilename();
                String storedFileNameItem = System.currentTimeMillis()+"-"+ originalFileNameItem;
                String savePath = "C:\\springboot_img_final\\"+storedFileNameItem;
                itemFile.transferTo(new File(savePath));
                ItemFileEntity itemFileEntity = ItemFileEntity.toSaveItemFileEntity(entity, originalFileNameItem, storedFileNameItem);
                itemFileRepository.save(itemFileEntity);
            }
        return savedId;
        }
    }
@Transactional
    public List<ItemDTO> findAll() {
      List<ItemEntity> itemEntityList = itemRepository.findAll();
      List<ItemDTO> itemDTOList = new ArrayList<>();
      for(ItemEntity itemEntity : itemEntityList){
          itemDTOList.add(ItemDTO.toItemDTO(itemEntity));
      }
      return itemDTOList;
    }
@Transactional
    public ItemDTO findById(Long id) {
    Optional<ItemEntity> itemEntityOptional = itemRepository.findById(id);
    if(itemEntityOptional.isPresent()){
        return ItemDTO.toItemDTO(itemEntityOptional.get());
    }else{
        return null;
    }
    }

}
