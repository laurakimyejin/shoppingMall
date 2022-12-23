package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.QuestionDTO;
import com.example.shoppingmall.entity.MemberEntity;
import com.example.shoppingmall.entity.QuestionEntity;
import com.example.shoppingmall.repository.MemberRepository;
import com.example.shoppingmall.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    public final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;

    public void save(QuestionDTO questionDTO) {
        Long id=questionDTO.getMemberId();
        Optional<MemberEntity>memberEntity =memberRepository.findById(id);
        if(memberEntity.isPresent()){
            MemberEntity member=memberEntity.get();
            questionRepository.save(QuestionEntity.toSaveEntity(questionDTO,member));
        }
    }

}
