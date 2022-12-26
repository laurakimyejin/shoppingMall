package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.ItemDTO;
import com.example.shoppingmall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping("/save")
    public String saveForm(@ModelAttribute ItemDTO itemDTO, Model model) {
        cartService.save(itemDTO);
        model.addAttribute("item", itemDTO);
        return "/cartPages/cartSave";
    }


}
