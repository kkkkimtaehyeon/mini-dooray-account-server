package com.nhnacademy.accountserver.service;

import com.nhnacademy.accountserver.dtos.AccountSaveRequestDto;
import com.nhnacademy.accountserver.entity.Member;
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
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword())); // 패스워드 암호화
        accountRepository.save(requestDto.toEntity(member));
    }
}
