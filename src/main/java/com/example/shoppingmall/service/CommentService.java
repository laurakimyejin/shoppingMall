package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.CommentDTO;
import com.example.shoppingmall.entity.CommentEntity;
import com.example.shoppingmall.entity.ItemEntity;
import com.example.shoppingmall.entity.MemberEntity;
import com.example.shoppingmall.repository.CommentRepository;
import com.example.shoppingmall.repository.ItemRepository;
import com.example.shoppingmall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long save(CommentDTO commentDTO) {
        ItemEntity itemEntity = itemRepository.findById(commentDTO.getItemId()).get();
        CommentEntity commentEntity = CommentEntity.toCommentEntity(itemEntity, commentDTO);
        Long id = commentRepository.save(commentEntity).getId();
        return id;
    }
@Transactional
    public List<CommentDTO> findAll(Long itemId) {
        ItemEntity itemEntity = itemRepository.findById(itemId).get();
        List<CommentEntity> commentEntities = itemEntity.getCommentEntityList();
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for(CommentEntity commentEntity : commentEntities){
            commentDTOList.add(CommentDTO.toCommentDTO(commentEntity));
        }
        return commentDTOList;
    }

    public Page<CommentDTO> commentPaging(Long id, Pageable pageable) {
        int page = pageable.getPageNumber()-1;
        final int pageLimit = 10;
//        Page<CommentEntity> commentEntities = commentRepository.findAll(page, pageLimit, Sort.by(Sort.Direction.DESC,"id")))
        return null;
    }
}

