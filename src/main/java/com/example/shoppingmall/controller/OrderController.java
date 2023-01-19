package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.CartItemDTO;
import com.example.shoppingmall.dto.ItemDTO;
import com.example.shoppingmall.dto.MemberDTO;
import com.example.shoppingmall.dto.OrderDTO;
import com.example.shoppingmall.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

//    주문페이지 이동
    @GetMapping("/order")
    public String order(){
        return "orderPages/order";
    }


    //주문상세페이지 이동
    @GetMapping("/order/save")
    public String saveForm(@ModelAttribute ItemDTO itemDTO, Model model) {
        System.out.println("itemDTO = " + itemDTO + ", model = " + model);
        model.addAttribute("item", itemDTO);
        return "orderPages/orderSave";
    }


//    카트리스트에서 주문페이지 이동하기
    @GetMapping("/order/cart")
    public String saveFormCart(@RequestParam("userId")String userId,@RequestParam("itemPriceTotal")int itemPriceTotal, Model model) {
        List<CartItemDTO>cartItemDTOList = orderService.findCartById(userId);
        System.out.println("cartItemDTOList = " + cartItemDTOList);
        model.addAttribute("cartList",cartItemDTOList);
        model.addAttribute("itemPriceTotal",itemPriceTotal);
        return "orderPages/orderSave2";
    }

    //카트리스트에서 체크박스로 선택 주문하기
    @PostMapping ("/order/cart2")
    public @ResponseBody String saveFormCart2(@RequestParam("cartList")JSONArray itemDTOList, HttpSession session) throws JSONException {
        Object member = session.getAttribute("member");
        System.out.println("체크값만 넘기기 = " + itemDTOList);
        member = (MemberDTO) member;
        String userId = ((MemberDTO) member).getUserId();
        String result = orderService.checkOrder(userId,itemDTOList);
        return result;
    }
    @GetMapping("/order/cart3")
    public String save3(@RequestParam("userId")String userId,Model model){
        List<CartItemDTO>cartItemDTOList=orderService.findByOrderReady(userId);
        int itemPriceTotal=cartItemDTOList.get(0).getItemPriceTotal();
        model.addAttribute("cartList",cartItemDTOList);
        model.addAttribute("itemPriceTotal",itemPriceTotal);

        return "orderPages/orderSave2";
    }


    @PostMapping("/order/save2")
    public @ResponseBody String save2(@RequestParam("cartList")JSONArray itemDTOList, Model model , HttpSession session) throws JSONException {
        Object member = session.getAttribute("member");
        member = (MemberDTO) member;
        String userId = ((MemberDTO) member).getUserId();
        System.out.println("userId11 = " + session.getAttribute("member"));
        System.out.println("userId22 = " + userId);
        System.out.println("itemDTOList2 = " + itemDTOList + ", model = " + model);
        orderService.save2(itemDTOList,userId);
        return "success";
    }



    //주문하기
    @PostMapping("/order/save")
    public String save(@ModelAttribute OrderDTO orderDTO, Model model) {
        System.out.println("시작orderDTO = " + orderDTO + ", model = " + model);
        orderService.save(orderDTO);
        model.addAttribute("order", orderDTO);
        System.out.println("저장후orderDTO = " + orderDTO + ", model = " + model);
        return "redirect:/";
    }

    //개인주문목록
    @GetMapping("/order/list")
    public String list(@RequestParam("userId")String userId, Model model) {
        List<OrderDTO>orderDTOList = orderService.findAll(userId);
        model.addAttribute("orderList", orderDTOList);
        return "orderPages/orderList";
    }

    //모든 주문목록
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

//    주문상태 변경
    @PostMapping("/order/status")
    public @ResponseBody String status(@RequestParam("id")Long id,@RequestParam("status")String status){
        orderService.update(id,status);
        return "success";
    }

    //쥐문취소
    @GetMapping("/order/cancel")
    public String cancel(@RequestParam("memberId")Long memberId){
        String userId = orderService.cancel(memberId);
        return "redirect:/cart/list?userId="+userId;
    }




}
