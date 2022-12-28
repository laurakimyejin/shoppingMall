package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.ItemDTO;
import com.example.shoppingmall.dto.OrderDTO;
import com.example.shoppingmall.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/order/save")
    public String saveForm(@ModelAttribute ItemDTO itemDTO, Model model) {
        System.out.println("itemDTO = " + itemDTO + ", model = " + model);
        model.addAttribute("item", itemDTO);
        return "orderPages/orderSave";
    }

    @PostMapping("/order/save")
    public String save(@ModelAttribute OrderDTO orderDTO, Model model) {
        System.out.println("orderDTO = " + orderDTO + ", model = " + model);
        orderService.save(orderDTO);
        model.addAttribute("order", orderDTO);
        return "/index";
    }

    @GetMapping("/order/list")
    public String list(@RequestParam("userId")String userId, Model model) {
        List<OrderDTO>orderDTOList = orderService.findAll(userId);
        model.addAttribute("orderList", orderDTOList);
        return "orderPages/orderList";
    }


}
