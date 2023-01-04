package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.ItemDTO;
import com.example.shoppingmall.dto.OrderDTO;
import com.example.shoppingmall.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/order")
    public String order(){
        return "orderPages/order";
    }


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
        return "orderPages/orderList2";
    }

    @GetMapping("/order/listAll")
    public String findListAll(@PageableDefault(page = 1,size = 3) Pageable pageable, Model model , @RequestParam(required = false , value = "sort", defaultValue = "id") String sort)
    {
        Page<OrderDTO>orderDTOList = orderService.findListAll(pageable, sort);
        model.addAttribute("orderList", orderDTOList);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < orderDTOList.getTotalPages()) ? startPage + blockLimit - 1 : orderDTOList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("sort", sort);
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("page", pageable.getPageNumber());

        return "orderPages/orderListAll";
    }




}
