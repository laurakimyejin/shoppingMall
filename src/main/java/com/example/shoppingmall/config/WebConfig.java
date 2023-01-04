package com.example.shoppingmall.config;

import com.example.shoppingmall.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private String resourcePath = "/upload/**";
    private String savePath = "file:///C:/springboot_img_final/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath).addResourceLocations(savePath);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor()) //인터셉터로 등록할 클래스
                .order(1) // 해당 인터셉터의 우선순위
                .addPathPatterns("/**") //인터셉터로 체크할 주소 (모든 주소)
                .excludePathPatterns("/", "/member/save", "/member/login", "/member/logout", "/item/main", "/js/**", "/css/**", "/jsp/**","/style/**", "/error", "/img/**","/product/**", "/*.ico", "/favicon/**"); //예외할 주소, 로그인 안해도 사용 가능
            }
        }

