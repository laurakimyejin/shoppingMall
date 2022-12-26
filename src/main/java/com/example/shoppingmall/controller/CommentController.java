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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/save")
    public ResponseEntity save(@RequestBody CommentDTO commentDTO){
        commentService.save(commentDTO);
        List<CommentDTO> commentDTOList =commentService.findAll(commentDTO.getItemId());
        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }
    @GetMapping("/comment/paging")
    public String commentPaging(@PageableDefault(page = 1) Pageable pageable, @RequestBody CommentDTO commentDTO, Model model){
       Page<CommentDTO> commentDTOS = commentService.commentPaging(commentDTO.getId(),pageable);
        int blockLimit = 3;
        //시작 페이지 값 계산
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        //끝 페이지 값 계산(3, 6, 9, 12---)
        //endPage 값이 totalPage값보다 크다면 endPage값을 totalPage값으로 덮어쓴다.
        int endPage = ((startPage + blockLimit - 1) < commentDTOS.getTotalPages()) ? startPage + blockLimit - 1 : commentDTOS.getTotalPages();

        model.addAttribute("commentList",commentDTOS);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "redirect:/item/detail";
    }
}
