package com.example.shoppingmall.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCheckInterceptor implements HandlerInterceptor {
    //인터셉터를 이용한 로그인 처리
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        //요청 객체                //응답 객체
//        String requestURL = request.getRequestURI();
//        이전페이지 요청
        String requestURL = request.getHeader("referer");
        //request에서 제공하는 메서드, 요청한 주소 가져와 준다.
        System.out.println("requestURL = " + requestURL);
        HttpSession session = request.getSession();
        //request에서 session 가져옴
        if(session.getAttribute("member") == null){
            response.sendRedirect("/member/login?redirectURL="+requestURL);
            //로그인 주소로 보내면서 로그인 끝나면 다시 돌아갈 주소도 함께 보냄
            return false;
        }
        return true;
    }
}
