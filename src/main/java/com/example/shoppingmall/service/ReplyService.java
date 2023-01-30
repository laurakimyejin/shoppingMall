package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.CommentDTO;
import com.example.shoppingmall.dto.ReplyDTO;
import com.example.shoppingmall.entity.CommentEntity;
import com.example.shoppingmall.entity.MemberEntity;
import com.example.shoppingmall.entity.QuestionEntity;
import com.example.shoppingmall.entity.ReplyEntity;
import com.example.shoppingmall.repository.MemberRepository;
import com.example.shoppingmall.repository.QuestionRepository;
import com.example.shoppingmall.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;
    @Transactional
    public List<ReplyDTO> save(ReplyDTO replyDTO) {
        MemberEntity memberEntity =memberRepository.findById(replyDTO.getMemberId()).get();
        QuestionEntity questionEntity = questionRepository.findById(replyDTO.getQuestionId()).get();
        questionEntity.setQuestionStatus("Y");
        ReplyEntity replyEntity = new ReplyEntity();
        replyEntity.setReplyContents(replyDTO.getReplyContents());
        replyEntity.setReplyName(replyDTO.getReplyName());
        replyEntity.setMemberEntity(memberEntity);
        replyEntity.setQuestionEntity(questionEntity);
        System.out.println("서비스 부분 replyDTO = " + replyDTO);
        replyRepository.save(replyEntity);
        List<ReplyEntity>replyEntityList=replyRepository.findByQuestionEntity(questionEntity);
        List<ReplyDTO>replyDTOList = new ArrayList<>();
        for (ReplyEntity replyEntity1 : replyEntityList) {
            ReplyDTO replyDTO1 = new ReplyDTO();
            replyDTO1.setReplyContents(replyEntity1.getReplyContents());
            replyDTO1.setReplyName(replyEntity1.getReplyName());
            replyDTO1.setReplyDate(replyEntity1.getCreatedTime());
            replyDTOList.add(replyDTO1);
        }
        return replyDTOList;

    }

    public void delete(Long id) {
        replyRepository.deleteById(id);
    }
}
