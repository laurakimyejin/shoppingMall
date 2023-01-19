package com.example.shoppingmall.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

@Controller
public class RestTestController {

    @GetMapping("/apis")
    public static String main(String[] args) throws IOException {
        System.out.println("api불러오기");
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1390804/NihhsTodayFlowerInfo01/selectTodayFlowerView01"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=BxPNlmkbBnAJUjFreSXMImZJIgkhIw6EoT4MuSIk%2FbZIdXq6yGoV5%2FdmJ3F6f5%2FxAM94q7sxwf1tK8gG%2BJIKog%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("dataNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*오늘의 꽃 인덱스*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
        return "/index";
    }
}
