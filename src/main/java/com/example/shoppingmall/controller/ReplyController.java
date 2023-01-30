package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.CommentDTO;
import com.example.shoppingmall.dto.ReplyDTO;
import com.example.shoppingmall.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

//    댓글 저장 및 댓글 리스트 불러오기
    @PostMapping("/reply/save")
    public @ResponseBody List<ReplyDTO> replySave(@ModelAttribute ReplyDTO replyDTO, Model model){
        List<ReplyDTO>replyDTOList=replyService.save(replyDTO);
        System.out.println("받아온replyDTO = " + replyDTO + ", model = " + model);
        model.addAttribute("replyList",replyDTOList);
        return replyDTOList;
    }

    //답변 삭제
    //questionController questionDetail이 @PathVariable이므로
    //redirect:/question/?questionId="+questionId X
    @GetMapping("/reply/delete")
    public String replyDelete(@RequestParam("replyId")Long id,@RequestParam("questionId")Long questionId){
        replyService.delete(id);
        return "redirect:/question/"+questionId;
    }


}
