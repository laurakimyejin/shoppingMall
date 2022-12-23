package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.MemberDTO;
import com.example.shoppingmall.entity.MemberEntity;
import com.example.shoppingmall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long save(MemberDTO memberDTO) {
        Long savedId = memberRepository.save(MemberEntity.toSaveEntity(memberDTO)).getId();
        return savedId;
    }

    public MemberDTO memberLogin(MemberDTO memberDTO) {
        System.out.println("memberDTO = " + memberDTO);
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByUserId(memberDTO.getUserId());
        if (optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity = optionalMemberEntity.get();
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){
                return MemberDTO.toDTO(memberEntity);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}