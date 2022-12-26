package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.ItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    @GetMapping("/save")
    public String saveForm(@ModelAttribute ItemDTO itemDTO, Model model){
        System.out.println("itemDTO = " + itemDTO + ", model = " + model);
        model.addAttribute("item",itemDTO);
        return "/cartPages/cartSave";
    }
}
