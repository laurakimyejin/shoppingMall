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

    @PostMapping("/duplicate-check-userId")
    public @ResponseBody String emailDuplicateCheck(@RequestParam("userId") String userId) {
        String checkResult = memberService.userIdDuplicateCheck(userId);
        if (checkResult.equals("success")) {
            return "success";
        } else {
            return "fail";
        }
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

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
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

    @GetMapping("/myPage")
    public String myPage(){
        return "/memberPages/myPage";
    }


}
