package com.example.shoppingmall.service;


import com.example.shoppingmall.dto.CrawlingDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrawlingService {
    private static String KOREA_COVID_DATAS_URL = "https://www.nihhs.go.kr/usr/persnal/Flower_library.do";


//    @PostConstruct
    public void getKoreaCovidDatas() throws IOException {

        Document doc = Jsoup.connect(KOREA_COVID_DATAS_URL).get();

        System.out.println(doc);

    }

}

