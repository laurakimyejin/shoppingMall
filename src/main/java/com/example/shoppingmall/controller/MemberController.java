package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.MemberDTO;
import com.example.shoppingmall.service.MemberService;
import com.example.shoppingmall.service.RegisterMail;
import com.example.shoppingmall.service.RegisterPassword;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final RegisterMail registerMail;
    private final RegisterPassword registerPassword;

    //회원가입 화면
    @GetMapping("/save")
    public String saveForm() {
        return "/memberPages/memberSave";
    }

    //userId 중복체크
    @PostMapping("/duplicate-check-userId")
    public @ResponseBody String userIdDuplicateCheck(@RequestParam("userId") String userId) {
        String checkResult = memberService.userIdDuplicateCheck(userId);
        if (checkResult.equals("success")) {
            return "success";
        } else {
            return "fail";
        }
    }

    //회원가입 처리
    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        memberService.save(memberDTO);
        return "redirect:/";
    }

    //일반 로그인 화면
    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "redirectURL", defaultValue = "/") String redirectURL, Model model) {
        model.addAttribute("redirectURL", redirectURL);
        return "memberPages/memberLogin";
    }

    //인터셉터
    @GetMapping("/login2")
    public String login2(@RequestParam(value = "redirectURL", defaultValue = "/") String redirectURL, Model model) {
        model.addAttribute("redirectURL", redirectURL);
        return "redirect:" + redirectURL;
    }

//    //로그인 화면
//    @GetMapping("/login")
//    public String loginForm() {
//        return "/memberPages/memberLogin";
//    }


    //로그인 처리
    @PostMapping("/login")
    public @ResponseBody String login(@ModelAttribute MemberDTO memberDTO, HttpSession session, @RequestParam(value = "redirectURL", defaultValue = "/") String redirectURL) {
        MemberDTO result = memberService.memberLogin(memberDTO);
        if (result != null) {
            session.setAttribute("member", result);
            System.out.println("redirect:" + redirectURL);
            return "ok";
        } else {
            return "no";
        }
    }

    //관리자 페이지
    @GetMapping("/admin")
    public String admin() {
        return "/memberPages/admin";
    }

    //회원 목록 페이징
    @GetMapping("/list")
    public String findAll(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<MemberDTO> memberDTOPage = memberService.findAll(pageable);
        model.addAttribute("memberList", memberDTOPage);

        int blockLimit = 3;
        //시작 페이지 값 계산
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        //끝 페이지 값 계산(3, 6, 9, 12---)
        //endPage 값이 totalPage값보다 크다면 endPage값을 totalPage값으로 덮어쓴다.
        int endPage = ((startPage + blockLimit - 1) < memberDTOPage.getTotalPages()) ? startPage + blockLimit - 1 : memberDTOPage.getTotalPages();

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "memberPages/memberList";
    }

    //회원 상세목록
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "memberPages/memberDetail";
    }

    //정보수정 클릭 시 비밀번호 확인 화면
    @GetMapping("/password")
    public String passwordCheck(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        MemberDTO memberDTO = memberService.findByUserId(userId);
        model.addAttribute("member", memberDTO);
        return "memberPages/memberPasswordCheck";
    }

    //회원 정보 수정 화면
    @GetMapping("/update")
    public String updateForm(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        MemberDTO memberDTO = memberService.findByUserId(userId);
        model.addAttribute("member", memberDTO);
        return "memberPages/memberUpdate";
    }

    //회원 정보 수정
    @PostMapping("/update")
    public String update(@ModelAttribute MemberDTO memberDTO,HttpSession session) {
//        String memberPassword = memberDTO.getMemberPasswordUpdate();
        String memberPassword = memberDTO.getMemberPassword();
        memberDTO.setMemberPassword(memberPassword);
        memberService.update(memberDTO);
        session.invalidate();
        return "index";
    }

    //카카오 로그인
    @PostMapping("/kakao")
    public String Home(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        System.out.println("memberDTO = " + memberDTO + ", session = " + session);
        MemberDTO member = memberService.saveKakao(memberDTO);
        member.setAccessToken(memberDTO.getAccessToken());
        session.setAttribute("member", member);
        session.setAttribute("access_token", memberDTO.getAccessToken());
        return "redirect:/";
    }

    //일반 로그아웃
    @GetMapping(value = "/logout")
    public String kakaoLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    //카카오 로그아웃
    @GetMapping("/logout2")
    public @ResponseBody String kakaoLogout2(HttpSession session) {
        session.invalidate();
        return "로그아웃";
    }

    // 이메일 중복체크, 이메일 인증
    @PostMapping("/mailConfirm")
    @ResponseBody
    String mailConfirm(@RequestParam("email") String email) throws Exception {
        //이메일 중복체크
        String checkResult = memberService.memberEmailDuplicateCheck(email);
        if (checkResult.equals("success")) {
            System.out.println("email = " + email);
            //이메일 인증
            String code = registerMail.sendSimpleMessage(email);
            System.out.println("인증코드 : " + code);
            return code;
        } else {
            return "fail";
        }
    }

    //비밀번호 찾기 화면
    @GetMapping("/searchPassword")
    public String searchPasswordForm() {
        return "memberPages/searchPassword";
    }

    //임시비밀번호 전송
    @PostMapping("/searchPassword")
    public @ResponseBody String searchPassword(@RequestParam("email") String email) throws Exception {
        //이메일 체크
        String checkResultPassword = memberService.findByMemberEmail(email);
        if (checkResultPassword.equals("success")) {
            System.out.println("email = " + email);
            //임시 비밀번호 전송
            String code = registerPassword.sendSimpleMessage(email);
            System.out.println("인증코드 : " + code);
            return code;
        }else{
            return "fail";
        }
    }

    //임시 비밀번호로 사용자 비밀번호 변경
    @PostMapping("/searchPasswordUpdate")
    public @ResponseBody void searchPasswordUpdate(@RequestParam("email")String memberEmail) throws Exception{
        System.out.println("넘어옴");
        String code = registerPassword.sendSimpleMessage(memberEmail);
        System.out.println("서비스전까지");
        memberService.passwordUpdate(memberEmail , code);
    }
}
