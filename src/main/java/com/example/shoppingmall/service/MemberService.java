package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.MemberDTO;
import com.example.shoppingmall.entity.CartEntity;
import com.example.shoppingmall.entity.MemberEntity;
import com.example.shoppingmall.repository.CartRepository;
import com.example.shoppingmall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;



    public Long save(MemberDTO memberDTO) {
        Long savedId = memberRepository.save(MemberEntity.toSaveEntity(memberDTO)).getId();
        Optional<MemberEntity> memberEntity = memberRepository.findById(savedId);
        if (memberEntity.isPresent()) {
            MemberEntity memberEntity1 = memberEntity.get();
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
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
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

    public Page<MemberDTO> findAll(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        final int pageLimit = 5;
        Page<MemberEntity> memberEntities = memberRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        Page<MemberDTO> memberDTOPage = memberEntities.map(
                member -> new MemberDTO(
                        member.getId(),
                        member.getUserId(),
                        member.getMemberEmail(),
                        member.getMemberName(),
                        member.getMemberMobile(),
                        member.getCreatedTime()));
        return memberDTOPage;
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> memberEntity = memberRepository.findById(id);
        if (memberEntity.isPresent()) {
            MemberEntity memberEntity1 = memberEntity.get();
            MemberDTO memberDTO = MemberDTO.toDTO(memberEntity1);
            return memberDTO;
        } else {
            return null;
        }
    }

    public MemberDTO findByUserId(String userId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByUserId(userId);
        if (optionalMemberEntity.isPresent()) {

            return MemberDTO.toDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    public void update(MemberDTO memberDTO) {
        MemberEntity updateEntity = MemberEntity.toUpdateEntity(memberDTO);
        memberRepository.save(updateEntity);
    }

    public MemberDTO saveKakao(MemberDTO memberDTO) {
        Optional<MemberEntity> memberEntity2 = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if (memberEntity2.isEmpty()) {
            MemberDTO memberDTO1 = new MemberDTO();
            memberDTO1.setMemberEmail(memberDTO.getMemberEmail());
            memberDTO1.setUserId(memberDTO.getUserId());
            memberDTO1.setMemberName(memberDTO.getMemberName());
            memberDTO1.setMemberPassword(memberDTO1.getUserId());
            memberDTO1.setMemberMobile("000-0000-0000");
            Long savedId = memberRepository.save(MemberEntity.toSaveEntity(memberDTO1)).getId();
            Optional<MemberEntity> memberEntity = memberRepository.findById(savedId);
            if (memberEntity.isPresent()) {
                MemberEntity memberEntity1 = memberEntity.get();
                CartEntity cartEntity = new CartEntity();
                cartEntity.setMemberEntity(memberEntity1);
                cartRepository.save(cartEntity);
                return MemberDTO.toDTO(memberEntity1);
            }
        } else {
            return MemberDTO.toDTO(memberEntity2.get());

        }
        return null;
    }

    public String memberEmailDuplicateCheck(String memberEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(memberEmail);
        if (optionalMemberEntity.isPresent()) {
            return "fail";
        } else {
            return "success";
        }
    }


}




