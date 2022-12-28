package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.MemberDTO;
import com.example.shoppingmall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/admin")
    public String admin(){
        return "/memberPages/admin";
    }

    @GetMapping("/list")
    public String findAll(@PageableDefault(page=1)Pageable pageable, Model model){
        Page<MemberDTO> memberDTOPage = memberService.findAll(pageable);
        model.addAttribute("memberList",memberDTOPage);

        int blockLimit = 3;
        //시작 페이지 값 계산
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        //끝 페이지 값 계산(3, 6, 9, 12---)
        //endPage 값이 totalPage값보다 크다면 endPage값을 totalPage값으로 덮어쓴다.
        int endPage = ((startPage + blockLimit - 1) < memberDTOPage.getTotalPages()) ? startPage + blockLimit - 1 : memberDTOPage.getTotalPages();

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "memberPages/memberList";
    }
}
