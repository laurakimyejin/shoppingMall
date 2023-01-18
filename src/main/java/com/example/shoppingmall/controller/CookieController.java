package com.example.shoppingmall.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CookieController {
    @GetMapping("/createCookie")
    public @ResponseBody String createCookie(HttpServletResponse response, @RequestParam("itemJson") JSONObject itemJson) throws JSONException {
        System.out.println("itemJson = " + itemJson);


// Accessing the value of "itemId"
        String itemId2 = itemJson.getString("itemId");
        Long itemId=Long.parseLong(itemId2);
        System.out.println(itemId); // Output: "20"
// Accessing the value of "itemImage"
        String itemImage = itemJson.getString("itemImage");
        System.out.println(itemImage); // Output: "1673825732974-images.jfif"
        // Create a cookie
        Cookie cookie = new Cookie("cookieName", "cookieValue");
        cookie.setValue(itemId2);
        cookie.setValue(itemImage);
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        response.addCookie(cookie);
        System.out.println("cookie = " + cookie);
        return "cookie created";
    }

    @GetMapping("/getCookie")
    public @ResponseBody String getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cookieName")) {
                    String cookieValue = cookie.getValue();
                    System.out.println(cookieValue);
                    break;
                }
            }
        }
        return "cookie found";
    }
}


