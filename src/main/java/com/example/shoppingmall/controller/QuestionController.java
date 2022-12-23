package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.QuestionDTO;
import com.example.shoppingmall.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;
    @GetMapping("/list")
    public String questionList(Model model){
        List<QuestionDTO> questionDTOList = questionService.findAll();
        model.addAttribute("questionList",questionDTOList);
        return "/questionPages/questionList";
    }

    @GetMapping("/save")
    public String questionSaveForm(){
        return "/questionPages/questionSave";
    }

    @PostMapping("/save")
    public String questionSave(@ModelAttribute QuestionDTO questionDTO){
        questionService.save(questionDTO);
        return "redirect:/question/list";
    }

}
