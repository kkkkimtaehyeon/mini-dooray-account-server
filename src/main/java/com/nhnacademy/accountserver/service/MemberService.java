package com.nhnacademy.accountserver.service;

import com.nhnacademy.accountserver.dtos.AccountSaveRequestDto;
import com.nhnacademy.accountserver.dtos.MemberResponseDto;
import com.nhnacademy.accountserver.dtos.MemberSaveRequestDto;
import com.nhnacademy.accountserver.entity.Member;
import com.nhnacademy.accountserver.enums.MemberStatus;
import com.nhnacademy.accountserver.exception.MemberAlreadyExistException;
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

        return new MemberResponseDto(savedMember.getMemberId(), savedMember.getEmail(), savedMember.getMemberStatus());
    }


    @Transactional
    public MemberResponseDto updateMember(Long memberId) {
        Member findMember = (memberRepository.findById(memberId)).get();

//        changeStatus
        findMember.changeStatus(MemberStatus.WITHDRAW);
        memberRepository.save(findMember);

        return new MemberResponseDto(memberId, findMember.getEmail(), findMember.getMemberStatus());
    }
}
