package com.example.shoppingmall.controller;


import com.example.shoppingmall.dto.TodayFlowerDTO;
import com.example.shoppingmall.dto.TodayFlowerListDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

@Controller
public class RestTestController {
    @GetMapping("/apis/todayFlower")
    public @ResponseBody TodayFlowerDTO todayFlower(String[] args , Model model) throws IOException, JSONException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1390804/NihhsTodayFlowerInfo01/selectTodayFlower01"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=BxPNlmkbBnAJUjFreSXMImZJIgkhIw6EoT4MuSIk%2FbZIdXq6yGoV5%2FdmJ3F6f5%2FxAM94q7sxwf1tK8gG%2BJIKog%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("fMonth", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*월(MM), 미지정시 오늘 날짜 적용됨*/
        urlBuilder.append("&" + URLEncoder.encode("fDay", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*일(DD), 미지정시 오늘 날짜 적용됨*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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

        JSONObject json = XML.toJSONObject(sb.toString());
        String jsonStr = json.toString(4);


        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonStr);
        JsonNode dataNode = rootNode.path("document").path("root").path("result");
        String dataNo = dataNode.path("dataNo").asText();
        String fContent = dataNode.path("fContent").asText();
        int fDay = dataNode.path("fDay").asInt();
        String fEngNm = dataNode.path("fEngNm").asText();
        String fGrow = dataNode.path("fGrow").asText();
        int fMonth = dataNode.path("fMonth").asInt();
        String fSctNm = dataNode.path("fSctNm").asText();
        String fType = dataNode.path("fType").asText();
        String fUse = dataNode.path("fUse").asText();
        String fileName1 = dataNode.path("fileName1").asText();
        String fileName2 = dataNode.path("fileName2").asText();
        String fileName3 = dataNode.path("fileName3").asText();
        String flowLang = dataNode.path("flowLang").asText();
        String flowNm = dataNode.path("flowNm").asText();
        String imgUrl1 = dataNode.path("imgUrl1").asText();
        String imgUrl2 = dataNode.path("imgUrl2").asText();
        String imgUrl3 = dataNode.path("imgUrl3").asText();
        TodayFlowerDTO todayFlowerDTO = new TodayFlowerDTO();
        todayFlowerDTO.setFDay(fDay);
        todayFlowerDTO.setFContent(fContent);
        todayFlowerDTO.setDataNo(dataNo);
        todayFlowerDTO.setFGrow(fGrow);
        todayFlowerDTO.setFEngNm(fEngNm);
        todayFlowerDTO.setFileName1(fileName1);
        todayFlowerDTO.setFlowLang(flowLang);
        todayFlowerDTO.setFileName2(fileName2);
        todayFlowerDTO.setFileName3(fileName3);
        todayFlowerDTO.setFUse(fUse);
        todayFlowerDTO.setFType(fType);
        todayFlowerDTO.setFSctNm(fSctNm);
        todayFlowerDTO.setFMonth(fMonth);
        todayFlowerDTO.setFlowNm(flowNm);
        todayFlowerDTO.setImgUrl1(imgUrl1);
        todayFlowerDTO.setImgUrl2(imgUrl2);
        todayFlowerDTO.setImgUrl3(imgUrl3);

        model.addAttribute("todayFlower",todayFlowerDTO);
        return todayFlowerDTO;
    }


    @GetMapping("/apis/todayFlowerList")
    public String todayFlowerList(@RequestParam("searchType")String searchType,@RequestParam("searchWord")String searchWord,
                                  @RequestParam(required = false , value = "pageNo", defaultValue = "1") String nowpageNo , Model model) throws IOException, JSONException {
        System.out.println("nowpageNo = " + nowpageNo);

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1390804/NihhsTodayFlowerInfo01/selectTodayFlowerList01"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=BxPNlmkbBnAJUjFreSXMImZJIgkhIw6EoT4MuSIk%2FbZIdXq6yGoV5%2FdmJ3F6f5%2FxAM94q7sxwf1tK8gG%2BJIKog%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(nowpageNo, "UTF-8")); /*페이지 번호(default : 1)*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수(default :10)*/
        urlBuilder.append("&" + URLEncoder.encode("fCsnt","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*초성*/
        urlBuilder.append("&" + URLEncoder.encode("fMonth","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*월 (MM)*/
        urlBuilder.append("&" + URLEncoder.encode("fDay","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*일 (DD)*/
        urlBuilder.append("&" + URLEncoder.encode("searchType","UTF-8") + "=" + URLEncoder.encode(searchType, "UTF-8")); /*1:꽃명(국문), 2:꽃명(학명), 3:꽃명(영문),4:꽃말, 5:내용, 6:이용, 7:기르기, 8:자생지, 9:내용전체*/
        urlBuilder.append("&" + URLEncoder.encode("searchWord","UTF-8") + "=" + URLEncoder.encode(searchWord, "UTF-8")); /*검색어*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

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

        JSONObject json = XML.toJSONObject(sb.toString());
        String jsonStr = json.toString(4);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonStr);
        JsonNode dataNode = rootNode.path("document").path("root").path("result");
        JsonNode dataNode2 = rootNode.path("document").path("root");

        int numOfRows = dataNode2.path("numOfRows").asInt();
        int pageNo = dataNode2.path("pageNo").asInt();
        int resultCnt = dataNode2.path("resultCnt").asInt();
        List<TodayFlowerListDTO>todayFlowerListDTOList = new ArrayList<>();
        if (resultCnt > 1 && resultCnt != 0) {
            for(int i=0;i<dataNode.size();i++) {
                int dataNo = dataNode.path(i).path("dataNo").asInt();
                int fDay = dataNode.path(i).path("fDay").asInt();
                int fMonth = dataNode.path(i).path("fMonth").asInt();
                String flowLang = dataNode.path(i).path("flowLang").asText();
                String flowNm = dataNode.path(i).path("flowNm").asText();
                String imgUrl1 = dataNode.path(i).path("imgUrl1").asText();
                TodayFlowerListDTO todayFlowerListDTO = new TodayFlowerListDTO();
                todayFlowerListDTO.setDataNo(dataNo);
                todayFlowerListDTO.setFDay(fDay);
                todayFlowerListDTO.setFMonth(fMonth);
                todayFlowerListDTO.setFlowLang(flowLang);
                todayFlowerListDTO.setFlowNm(flowNm);
                todayFlowerListDTO.setImgUrl1(imgUrl1);
                todayFlowerListDTOList.add(todayFlowerListDTO);
        }
        } else if (resultCnt == 1) {
            int dataNo = dataNode.path("dataNo").asInt();
            int fDay = dataNode.path("fDay").asInt();
            int fMonth = dataNode.path("fMonth").asInt();
            String flowLang = dataNode.path("flowLang").asText();
            String flowNm = dataNode.path("flowNm").asText();
            String imgUrl1 = dataNode.path("imgUrl1").asText();
            TodayFlowerListDTO todayFlowerListDTO = new TodayFlowerListDTO();
            todayFlowerListDTO.setDataNo(dataNo);
            todayFlowerListDTO.setFDay(fDay);
            todayFlowerListDTO.setFMonth(fMonth);
            todayFlowerListDTO.setFlowLang(flowLang);
            todayFlowerListDTO.setFlowNm(flowNm);
            todayFlowerListDTO.setImgUrl1(imgUrl1);
            todayFlowerListDTOList.add(todayFlowerListDTO);
        }else {
            model.addAttribute("list", "no");
            return "/apiPages/todayFlowerList";
        }

        model.addAttribute("todayFlowerList",todayFlowerListDTOList);
        model.addAttribute("PageNo",pageNo);
        model.addAttribute("searchType",searchType);
        model.addAttribute("searchWord",searchWord);
        System.out.println("pageNo : " + pageNo);



        //전체 글 갯수 조회
//       int pageNo = dataNode2.path("pageNo").asInt();
        //필요 페이지 계산
        int maxPage = (int)(Math.ceil((double)resultCnt / 10));

        //시작 페이지 값 계산
        int startPage = (((int)(Math.ceil((double) pageNo / 10))) - 1) *10 + 1;
        //끝 페이지 값 계산
        int endPage = startPage + 9;
        if(endPage>maxPage){
            endPage = maxPage;
        }

        model.addAttribute("pageNo",pageNo);
        model.addAttribute("maxPage",maxPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);






        return "/apiPages/todayFlowerList";
    }



    @GetMapping("/apis/todayFlowerView")
    public String todayFlowerView(@RequestParam("dataNo")String dataNo , Model model) throws IOException, JSONException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1390804/NihhsTodayFlowerInfo01/selectTodayFlowerView01"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=BxPNlmkbBnAJUjFreSXMImZJIgkhIw6EoT4MuSIk%2FbZIdXq6yGoV5%2FdmJ3F6f5%2FxAM94q7sxwf1tK8gG%2BJIKog%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("dataNo","UTF-8") + "=" + URLEncoder.encode(dataNo, "UTF-8")); /*오늘의 꽃 인덱스*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

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

        JSONObject json = XML.toJSONObject(sb.toString());
        String jsonStr = json.toString(4);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonStr);
        JsonNode dataNode = rootNode.path("document").path("root").path("result");
        String fContent = dataNode.path("fContent").asText();
        int fDay = dataNode.path("fDay").asInt();
        String fEngNm = dataNode.path("fEngNm").asText();
        String fGrow = dataNode.path("fGrow").asText();
        int fMonth = dataNode.path("fMonth").asInt();
        String fSctNm = dataNode.path("fSctNm").asText();
        String fType = dataNode.path("fType").asText();
        String fUse = dataNode.path("fUse").asText();
        String fileName1 = dataNode.path("fileName1").asText();
        String fileName2 = dataNode.path("fileName2").asText();
        String fileName3 = dataNode.path("fileName3").asText();
        String flowLang = dataNode.path("flowLang").asText();
        String flowNm = dataNode.path("flowNm").asText();
        String imgUrl1 = dataNode.path("imgUrl1").asText();
        String imgUrl2 = dataNode.path("imgUrl2").asText();
        String imgUrl3 = dataNode.path("imgUrl3").asText();
        TodayFlowerDTO todayFlowerDTO = new TodayFlowerDTO();
        todayFlowerDTO.setFDay(fDay);
        todayFlowerDTO.setFContent(fContent);
        todayFlowerDTO.setDataNo(dataNo);
        todayFlowerDTO.setFGrow(fGrow);
        todayFlowerDTO.setFEngNm(fEngNm);
        todayFlowerDTO.setFileName1(fileName1);
        todayFlowerDTO.setFlowLang(flowLang);
        todayFlowerDTO.setFileName2(fileName2);
        todayFlowerDTO.setFileName3(fileName3);
        todayFlowerDTO.setFUse(fUse);
        todayFlowerDTO.setFType(fType);
        todayFlowerDTO.setFSctNm(fSctNm);
        todayFlowerDTO.setFMonth(fMonth);
        todayFlowerDTO.setFlowNm(flowNm);
        todayFlowerDTO.setImgUrl1(imgUrl1);
        todayFlowerDTO.setImgUrl2(imgUrl2);
        todayFlowerDTO.setImgUrl3(imgUrl3);

        model.addAttribute("todayFlower",todayFlowerDTO);
        return "/apiPages/todayFlowerView";
    }





}
