package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.CommentDTO;
import com.example.shoppingmall.dto.ItemDTO;
import com.example.shoppingmall.service.CommentService;
import com.example.shoppingmall.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final CommentService commentService;

    //상품 저장 화면
    @GetMapping("/item/save")
    public String saveForm(){
        return "itemPages/itemSave";
    }
    //상품 저장처리
    @PostMapping("/item/save")
    public String save(@ModelAttribute ItemDTO itemDTO) throws IOException {
        itemService.save(itemDTO);
        return "redirect:/item/main";
    }
    //상품 메인
    @GetMapping("/item/main")
    public String findAll(@PageableDefault(page = 1,size = 5)Pageable pageable, Model model , @RequestParam(required = false , value = "sort", defaultValue = "id") String sort){
        Page<ItemDTO> itemDTOList = itemService.findAll(pageable, sort);
        model.addAttribute("itemList",itemDTOList);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < itemDTOList.getTotalPages()) ? startPage + blockLimit - 1 : itemDTOList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("sort", sort);
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("page", pageable.getPageNumber());
        return "itemPages/itemMain";
    }
    //상품 상세조회
    @GetMapping("/item/")
    public String findById(@PageableDefault(page = 1) Pageable pageable, @RequestParam("itemId") Long itemId, Model model){
        System.out.println( "itemId = " + itemId);
        ItemDTO itemDTO = itemService.findById(itemId);
        model.addAttribute("item",itemDTO);
    //리뷰 리스트 페이징
        Page<CommentDTO> commentDTOList = commentService.findAll(itemId,pageable);
        if (!commentDTOList.isEmpty()) {
            model.addAttribute("commentList", commentDTOList);
            int blockLimit = 3;
            //시작 페이지 값 계산
            int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
            //끝 페이지 값 계산(3, 6, 9, 12---)
            //endPage 값이 totalPage값보다 크다면 endPage값을 totalPage값으로 덮어쓴다.
            int endPage = ((startPage + blockLimit - 1) < commentDTOList.getTotalPages()) ? startPage + blockLimit - 1 : commentDTOList.getTotalPages();
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("itemId",itemId);
        }else {
            model.addAttribute("commentList", "empty");
        }
       return "itemPages/itemDetail";
    }
    //상품 수정 화면
    @GetMapping("/item/update/{id}")
    public String updateForm(@PathVariable Long id, Model model){
       ItemDTO itemDTO = itemService.findById(id);
        model.addAttribute("item",itemDTO);
        return "itemPages/itemUpdate";
    }
    //상품 수정 처리
    @PostMapping("/item/update")
    public String update (@ModelAttribute ItemDTO itemDTO,Model model) throws IOException{
        System.out.println("itemDTO = " + itemDTO + ", model = " + model);
        itemService.update(itemDTO);
        ItemDTO itemDTO1 = itemService.findById(itemDTO.getId());
        model.addAttribute("board",itemDTO1);
        return "redirect:/item/main";
    }
    //상품 삭제
    @GetMapping("/item/delete/{id}")
    public String delete(@PathVariable Long id){
        itemService.delete(id);
        return"redirect:/item/main";
    }

    //주문확인에서 아이템조회
    @GetMapping("/items")
    public String findById(@PageableDefault(page = 1) Pageable pageable, @RequestParam("orderName") String orderName, Model model){
        System.out.println("pageable = " + pageable + ", orderName = " + orderName + ", model = " + model);
        ItemDTO itemDTO = itemService.findByOrderName(orderName);
        Long itemId = itemDTO.getId();
        model.addAttribute("item",itemDTO);
        Page<CommentDTO> commentDTOList = commentService.findAll(itemId,pageable);
        if (!commentDTOList.isEmpty()) {
            model.addAttribute("commentList", commentDTOList);
            int blockLimit = 3;
            //시작 페이지 값 계산
            int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
            //끝 페이지 값 계산(3, 6, 9, 12---)
            //endPage 값이 totalPage값보다 크다면 endPage값을 totalPage값으로 덮어쓴다.
            int endPage = ((startPage + blockLimit - 1) < commentDTOList.getTotalPages()) ? startPage + blockLimit - 1 : commentDTOList.getTotalPages();
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("itemId",itemId);
        }else {
            model.addAttribute("commentList", "empty");
        }
        return "itemPages/itemDetail";
    }

}
