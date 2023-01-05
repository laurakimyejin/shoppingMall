package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.ReplyDTO;
import com.example.shoppingmall.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping("/reply/save")
    public @ResponseBody List<ReplyDTO> replySave(@ModelAttribute ReplyDTO replyDTO, Model model){
        List<ReplyDTO>replyDTOList=replyService.save(replyDTO);
        System.out.println("받아온replyDTO = " + replyDTO + ", model = " + model);
        model.addAttribute("replyList",replyDTOList);
        return replyDTOList;
    }
}
