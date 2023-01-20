package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.CrawlingDTO;
import com.example.shoppingmall.service.CrawlingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class CrawlingController {
//    private final CrawlingService crawlingService;
//
//    @GetMapping("/korea")
//    public String korea(Model model) throws IOException {
//
//        List<CrawlingDTO> koreaStatsList = crawlingService.getKoreaCovidDatas();
//
//        model.addAttribute("koreaStats", koreaStatsList);
//
//        return "korea";
//
//    }
}
