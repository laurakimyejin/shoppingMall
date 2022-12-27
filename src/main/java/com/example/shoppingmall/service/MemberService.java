package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.MemberDTO;
import com.example.shoppingmall.entity.CartEntity;
import com.example.shoppingmall.entity.MemberEntity;
import com.example.shoppingmall.repository.CartRepository;
import com.example.shoppingmall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;

    public Long save(MemberDTO memberDTO) {
        Long savedId = memberRepository.save(MemberEntity.toSaveEntity(memberDTO)).getId();
        Optional<MemberEntity>memberEntity=memberRepository.findById(savedId);
        if (memberEntity.isPresent()){
            MemberEntity memberEntity1=memberEntity.get();
            CartEntity cartEntity = new CartEntity();
            cartEntity.setMemberEntity(memberEntity1);
            cartRepository.save(cartEntity);
        }

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

    public String userIdDuplicateCheck(String userId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByUserId(userId);
        if (optionalMemberEntity.isPresent()) {
            return "fail";
        } else {
            return "success";
        }
    }
}