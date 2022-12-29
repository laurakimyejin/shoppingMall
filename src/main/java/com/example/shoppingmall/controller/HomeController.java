package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/kakao")
    public String Home(@RequestParam("userId")Long userId, HttpSession session){
        MemberDTO memberDTO = new MemberDTO();
        System.out.println("userId = " + userId + ", session = " + session);
        memberDTO.setId(userId);
        session.setAttribute("member",memberDTO);
        return "index";
    }
}
