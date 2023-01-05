package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.QuestionDTO;
import com.example.shoppingmall.entity.MemberEntity;
import com.example.shoppingmall.entity.QuestionEntity;
import com.example.shoppingmall.repository.MemberRepository;
import com.example.shoppingmall.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    public final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;

    public void save(QuestionDTO questionDTO) {
        Long id = questionDTO.getMemberId();
        Optional<MemberEntity> memberEntity = memberRepository.findById(id);
        if (memberEntity.isPresent()) {
            MemberEntity member = memberEntity.get();
            questionRepository.save(QuestionEntity.toSaveEntity(questionDTO, member));
        }
    }

    @Transactional
    public Page<QuestionDTO> findAll(Pageable pageable, String sort, String search, String category) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = pageable.getPageSize();
        System.out.println("pageable = " + pageable + ", sort = " + sort + ", search = " + search + ", category = " + category);
//        Containing
        if (category.equals("questionTitle")) {
            Page<QuestionEntity> questionEntityList = questionRepository.findByQuestionTitleContaining(search, PageRequest.of(page, pageLimit, Sort.by(sort).descending()));
            Page<QuestionDTO> questionDTOList = questionEntityList.map(QuestionDTO::toQuestionDTO);
            return questionDTOList;
        } else if (category.equals("questionContents")) {
            Page<QuestionEntity> questionEntityList = questionRepository.findByQuestionContentsContaining(search, PageRequest.of(page, pageLimit, Sort.by(sort).descending()));
            Page<QuestionDTO> questionDTOList = questionEntityList.map(QuestionDTO::toQuestionDTO);
            return questionDTOList;
        } else if (category.equals("questionWriter")) {
            Page<QuestionEntity> questionEntityList = questionRepository.findByQuestionNameContaining(search, PageRequest.of(page, pageLimit, Sort.by(sort).descending()));
            Page<QuestionDTO> questionDTOList = questionEntityList.map(QuestionDTO::toQuestionDTO);
            return questionDTOList;
        } else {
            Page<QuestionEntity> questionEntityList = questionRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, sort)));
            Page<QuestionDTO> questionDTOList = questionEntityList.map(QuestionDTO::toQuestionDTO);
            return questionDTOList;

        }
    }


    public QuestionDTO findById(Long id) {
        Optional<QuestionEntity> questionEntity = questionRepository.findById(id);
        if (questionEntity.isPresent()) {
            QuestionEntity question = questionEntity.get();
            return QuestionDTO.toQuestionDTO(question);
        }
        return null;
    }
}
