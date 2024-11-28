package com.nhnacademy.accountserver.service;

import com.nhnacademy.accountserver.dtos.*;
import com.nhnacademy.accountserver.entity.Account;
import com.nhnacademy.accountserver.entity.Member;
import com.nhnacademy.accountserver.enums.MemberStatus;
import com.nhnacademy.accountserver.exception.MemberAlreadyExistException;
import com.nhnacademy.accountserver.exception.MemberNotFoundException;
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
        // 중복된 회원(이메일) 검증
        String email = memberSaveRequestDto.getEmail();
        if (memberRepository.existsByEmail(email)) {
            throw new MemberAlreadyExistException(email);
        }
        // 회원 -> 계정 순으로 DB에 저장
        Member savedMember = memberRepository.save(memberSaveRequestDto.toEntity());
        AccountSaveRequestDto accountSaveRequestDto = new AccountSaveRequestDto(memberSaveRequestDto);
        accountService.createAccount(accountSaveRequestDto, savedMember);

        return new MemberResponseDto(savedMember);
    }


    @Transactional
    public MemberResponseDto updateMemberStatus(Long memberId, MemberStatus memberStatus) {
        Member foundMember = (memberRepository.findById(memberId)).orElseThrow(() -> new MemberNotFoundException("메세지 넣으삼"));

//        changeStatus
        foundMember.changeStatus(memberStatus);
        memberRepository.save(foundMember);

        return new MemberResponseDto(foundMember);
    }

    public MemberResponseDto updateMember(long memberId, MemberUpdateRequestDto updateRequestDto) {
        Member foundMember = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException("메세지 넣으삼"));

        Member updatedMember = memberRepository.save(new Member(memberId, updateRequestDto.getEmail(), foundMember.getMemberStatus()));
        Account foundAccount = accountService.getAccount(memberId);
        accountService.updatePassword(foundAccount, foundAccount.getPassword());

        return new MemberResponseDto(updatedMember);

    }


}
