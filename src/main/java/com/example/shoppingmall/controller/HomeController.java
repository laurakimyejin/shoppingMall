package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.ItemDTO;
import com.example.shoppingmall.dto.MemberDTO;
import com.example.shoppingmall.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    final ItemService itemService;

    @GetMapping("/")
    public String findAll(@PageableDefault(page = 1) Pageable pageable, Model model , @RequestParam(required = false , value = "sort", defaultValue = "id") String sort
            , @RequestParam(required = false , value = "search", defaultValue = "") String search,
                          @RequestParam(required = false , value = "category", defaultValue = "") String category){
        Page<ItemDTO> itemDTOList = itemService.findAll(pageable, sort , search , category);
        if (itemDTOList.isEmpty()){
            model.addAttribute("message", "null");
        }
        model.addAttribute("itemList",itemDTOList);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < itemDTOList.getTotalPages()) ? startPage + blockLimit - 1 : itemDTOList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("sort", sort);
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("page", pageable.getPageNumber());
        System.out.println("itemList" + itemDTOList);


        return "index";
    }


}
