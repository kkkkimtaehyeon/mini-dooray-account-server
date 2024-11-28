package com.nhnacademy.accountserver.service;

import com.nhnacademy.accountserver.dtos.MemberResponseDto;
import com.nhnacademy.accountserver.dtos.MemberUpdateRequestDto;
import com.nhnacademy.accountserver.entity.Member;
import com.nhnacademy.accountserver.respository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponseDto updateMember(long id, MemberUpdateRequestDto updateRequestDto) {
        // 멤버 찾아서 존재 여부 확인
        Member member = memberRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("회원이 존재하지 않습니다."));
        // 있으면 수정
        MemberResponseDto dto = MemberResponseDto.builder()
                .id(updateRequestDto.getId())
                .email(updateRequestDto.getEmail())
                .build();

        return dto;
    }
}
