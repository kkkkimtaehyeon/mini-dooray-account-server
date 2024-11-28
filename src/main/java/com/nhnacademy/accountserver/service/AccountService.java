package com.nhnacademy.accountserver.service;

import com.nhnacademy.accountserver.dtos.AccountLoginRequest;
import com.nhnacademy.accountserver.dtos.AccountResponseDto;
import com.nhnacademy.accountserver.dtos.AccountSaveRequestDto;

import com.nhnacademy.accountserver.dtos.AccountUpdateRequestDto;
import com.nhnacademy.accountserver.entity.Account;
import com.nhnacademy.accountserver.entity.Member;
import com.nhnacademy.accountserver.exception.MemberAccountNotFoundException;
import com.nhnacademy.accountserver.exception.MemberAlreadyExistException;
import com.nhnacademy.accountserver.respository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public void createAccount(AccountSaveRequestDto requestDto, Member member) {
        String id = requestDto.getId();
        if (accountRepository.existsById(id)) {
            throw new MemberAlreadyExistException(id);
        }
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        accountRepository.save(requestDto.toEntity(member));
    }

    public Account getAccount(long memberId) {
        return accountRepository.findByMember_MemberId(memberId).orElseThrow(MemberAccountNotFoundException::new);
    }

    //반환 dto로 바꿔야됨 found account, 회원 조회, member
    public void updatePassword(AccountUpdateRequestDto updateRequestDto, String password) {
        accountRepository.save(updateRequestDto.toEntity());
    }

    public AccountResponseDto login(AccountLoginRequest loginRequest) {

        AccountResponseDto accountResponseDto =  accountRepository.findById((loginRequest.getId()));

        if (!passwordEncoder.matches(loginRequest.getPassword(), accountResponseDto.getPassword())) {
            throw new RuntimeException("Incorerect Password");
        }

        return accountResponseDto;
    }


}
