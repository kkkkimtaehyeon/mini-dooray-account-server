package com.nhnacademy.accountserver.service;

import com.nhnacademy.accountserver.dtos.AccountSaveRequestDto;
import com.nhnacademy.accountserver.entity.Member;
import com.nhnacademy.accountserver.respository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public void createAccount(AccountSaveRequestDto requestDto, Member member) {
        accountRepository.save(requestDto.toEntity(member));
    }
}
