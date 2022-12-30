package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.CartDTO;
import com.example.shoppingmall.dto.CartItemDTO;
import com.example.shoppingmall.dto.ItemDTO;
import com.example.shoppingmall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    //장바구니 저장
    @GetMapping("/save")
    public String saveForm(@ModelAttribute ItemDTO itemDTO, Model model) {
        cartService.save(itemDTO);
        model.addAttribute("item", itemDTO);
        return "/cartPages/cartSave";
    }
    //장바구니 리스트
    @GetMapping("/list")
    public String listForm(@RequestParam("userId") String userId,Model model) {
        List<CartItemDTO>cartItemDTOList = cartService.findAll(userId);
        model.addAttribute("cartList", cartItemDTOList);
        return "/cartPages/cartList";
    }

    //장바구니 상세페이지
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model){
        CartItemDTO cartDTO = cartService.findById(id);
        model.addAttribute("cart",cartDTO);
        return "cartPages/cartList";
    }

    //장바구니 삭제
    @GetMapping("/delete")
    public String delete(@RequestParam("cartId")Long id, @RequestParam("userId")String userId){
        System.out.println("id = " + id + ", userId = " + userId);
        cartService.delete(id);
        return "redirect:/cart/list?userId="+userId;
    }

    //장바구니 수정
    @GetMapping("/update")
    public String update(@ModelAttribute ItemDTO itemDTO, Model model){
        cartService.update(itemDTO);
        System.out.println("itemDTO = " + itemDTO);
        CartItemDTO cartDTO = cartService.findById(itemDTO.getId());
        model.addAttribute("cart",cartDTO);
        return "redirect:/cart/list?userId="+itemDTO.getUserId();
    }

}
