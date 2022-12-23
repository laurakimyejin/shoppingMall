package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.QuestionDTO;
import com.example.shoppingmall.entity.MemberEntity;
import com.example.shoppingmall.entity.QuestionEntity;
import com.example.shoppingmall.repository.MemberRepository;
import com.example.shoppingmall.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

    public List<QuestionDTO> findAll() {
        List<QuestionEntity> questionEntityList = questionRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (QuestionEntity questionEntity : questionEntityList) {
            QuestionDTO boardDTO = QuestionDTO.toDTO(questionEntity);
            questionDTOList.add(boardDTO);
        }
        return questionDTOList;
    }
}
