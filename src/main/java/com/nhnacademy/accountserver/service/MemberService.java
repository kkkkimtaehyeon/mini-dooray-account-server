package com.nhnacademy.accountserver.service;

import com.nhnacademy.accountserver.dtos.AccountSaveRequestDto;
import com.nhnacademy.accountserver.dtos.MemberResponseDto;
import com.nhnacademy.accountserver.dtos.MemberSaveRequestDto;
import com.nhnacademy.accountserver.entity.Member;
import com.nhnacademy.accountserver.respository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final AccountService accountService;

    @Transactional
    public MemberResponseDto createMember(MemberSaveRequestDto memberSaveRequestDto) {
        Member savedMember = memberRepository.save(memberSaveRequestDto.toEntity());
        AccountSaveRequestDto accountSaveRequestDto = new AccountSaveRequestDto(memberSaveRequestDto);
        accountService.createAccount(accountSaveRequestDto, savedMember);

        return new MemberResponseDto(savedMember.getMemberId(), savedMember.getEmail(), savedMember.getMemberStatus());
    }



}
