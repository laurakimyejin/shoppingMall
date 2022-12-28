package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.CommentDTO;
import com.example.shoppingmall.dto.PageDTO;
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

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/save")
    public @ResponseBody Map<String, Object> save(@ModelAttribute CommentDTO commentDTO, @PageableDefault(page = 1) Pageable pageable){
        commentService.save(commentDTO);
        System.out.println("commentDTO = " + commentDTO + ", pageable = " + pageable);
        Page<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getItemId(),pageable);
        if (!commentDTOList.isEmpty()) {
            int blockLimit = 3;
            //시작 페이지 값 계산
            int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
            //끝 페이지 값 계산(3, 6, 9, 12---)
            //endPage 값이 totalPage값보다 크다면 endPage값을 totalPage값으로 덮어쓴다.
            int endPage = ((startPage + blockLimit - 1) < commentDTOList.getTotalPages()) ? startPage + blockLimit - 1 : commentDTOList.getTotalPages();
            Map<String,Object> pagingMap = new HashMap<>();
            PageDTO pageDTO = new PageDTO();
            pageDTO.setEndPage(endPage);
            pageDTO.setStartPage(startPage);
            pageDTO.setItemId(commentDTO.getItemId());
            pagingMap.put("commentList",commentDTOList);
            pagingMap.put("paging",pageDTO);
            System.out.println(pagingMap);
            return pagingMap;
        }else {
        return null;
        }
    }

    @GetMapping("/comment/list")
    public String list(Model model){
        List<CommentDTO> commentDTOList = commentService.list();
        return "commentPages/commentList";
    }

}
