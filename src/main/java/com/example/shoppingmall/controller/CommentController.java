package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.CommentDTO;
import com.example.shoppingmall.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/save")
    public @ResponseBody Page<CommentDTO> save(@ModelAttribute CommentDTO commentDTO,@PageableDefault(page = 1) Pageable pageable){
        commentService.save(commentDTO);
        System.out.println("commentDTO = " + commentDTO + ", pageable = " + pageable);
        Page<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getItemId(),pageable);
        System.out.println("commentDTOList = " + commentDTOList.get());
        System.out.println("commentDTOList.getTotalElements() = " + commentDTOList.getTotalElements());
        System.out.println("commentDTOList.getContent()" + commentDTOList.getContent());
        return commentDTOList;
    }

}
