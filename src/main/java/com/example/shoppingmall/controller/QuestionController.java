package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.MemberDTO;
import com.example.shoppingmall.dto.OrderDTO;
import com.example.shoppingmall.dto.QuestionDTO;
import com.example.shoppingmall.dto.ReplyDTO;
import com.example.shoppingmall.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;

//    문의 리스트 불러오기
    @GetMapping("/list")
    public String questionList(@PageableDefault(page = 1,size = 3) Pageable pageable, Model model , @RequestParam(required = false , value = "sort", defaultValue = "id") String sort
            , @RequestParam(required = false , value = "search", defaultValue = "") String search,
                               @RequestParam(required = false , value = "category", defaultValue = "") String category){
        Page<QuestionDTO> questionDTOList = questionService.findAll(pageable, sort , search , category);
        model.addAttribute("questionList",questionDTOList);
        if(questionDTOList.getTotalElements() == 0){
            model.addAttribute("message","null");
        }
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < questionDTOList.getTotalPages()) ? startPage + blockLimit - 1 : questionDTOList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("sort", sort);
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("search", search);
        model.addAttribute("category", category);
        return "/questionPages/questionList";
    }


// 문의글 작성하기 페이지 이동
    @GetMapping("/save")
    public String questionSaveForm(){
        return "/questionPages/questionSave";
    }


    // 문의글 작성하기
    @PostMapping("/save")
    public String questionSave(@ModelAttribute QuestionDTO questionDTO){
        questionService.save(questionDTO);
        return "redirect:/question/list";
    }
//    문의글 상세보기
    @GetMapping("/{id}")
    public String questionDetail(@PathVariable Long id, Model model){
        QuestionDTO questionDTO = questionService.findById(id);
        List<ReplyDTO>replyDTOList = questionService.findReplyById(id);
        model.addAttribute("question", questionDTO);
        model.addAttribute("replyList", replyDTOList);
        return "/questionPages/questionDetail";
    }

//    문의글 수정하기

    @GetMapping("/update/{id}")
    public String questionUpdateForm(@PathVariable Long id, Model model){
        System.out.println("넘오엄");
        QuestionDTO questionDTO = questionService.findById(id);
        model.addAttribute("question", questionDTO);
        return "/questionPages/questionUpdate";
    }

    @PostMapping("/update")
    public String questionUpdate(@ModelAttribute QuestionDTO questionDTO){
        questionService.update(questionDTO);
        return "redirect:/question/list";
    }

    @GetMapping("/delete/{id}")
    public String questionDelete(@PathVariable Long id){
        questionService.delete(id);
        return "redirect:/question/list";
    }


}
