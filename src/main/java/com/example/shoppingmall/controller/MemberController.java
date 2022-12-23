package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.MemberDTO;
import com.example.shoppingmall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/save")
    public String saveForm(){
        return "/memberPages/memberSave";
    }

    @PostMapping("save")
    public String save(@ModelAttribute MemberDTO memberDTO){
        memberService.save(memberDTO);
        return "index";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "/memberPages/memberLogin";
    }

    @PostMapping("/login")
    public @ResponseBody String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        MemberDTO result = memberService.memberLogin(memberDTO);
        if(result!=null){
            session.setAttribute("member",result);
            return "ok";
        }else {
            return "no";
        }
    }

}
