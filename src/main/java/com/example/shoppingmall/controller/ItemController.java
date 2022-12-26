package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.CommentDTO;
import com.example.shoppingmall.dto.ItemDTO;
import com.example.shoppingmall.service.CommentService;
import com.example.shoppingmall.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final CommentService commentService;

    @GetMapping("/item/save")
    public String saveForm(){
        return "itemPages/itemSave";
    }

    @PostMapping("/item/save")
    public String save(@ModelAttribute ItemDTO itemDTO) throws IOException {
        itemService.save(itemDTO);
        return "redirect:/item/main";
    }
    @GetMapping("/item/main")
    public String findAll(Model model){
        List<ItemDTO> itemDTOList = itemService.findAll();
        model.addAttribute("itemList",itemDTOList);
        return "itemPages/itemMain";
    }

    @GetMapping("/item/{id}")
    public String findById(@PathVariable Long id, Model model){
       ItemDTO itemDTO = itemService.findById(id);
       model.addAttribute("item",itemDTO);
        List<CommentDTO> commentDTOList = commentService.findAll(id);
        if (commentDTOList.size() > 0) {
            model.addAttribute("commentList", commentDTOList);
        }else {
            model.addAttribute("commentList", "empty");
        }
       return "itemPages/itemDetail";
    }

}
