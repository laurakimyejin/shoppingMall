package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.CartItemDTO;
import com.example.shoppingmall.dto.ItemDTO;
import com.example.shoppingmall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping("/save")
    public String saveForm(@ModelAttribute ItemDTO itemDTO, Model model) {
        cartService.save(itemDTO);
        model.addAttribute("item", itemDTO);
        return "/cartPages/cartSave";
    }

    @GetMapping("/list")
    public String listForm(@RequestParam("userId") String userId,Model model) {
        List<CartItemDTO>cartItemDTOList = cartService.findAll(userId);
        model.addAttribute("cartList", cartItemDTOList);
        return "/cartPages/cartList";
    }



}
