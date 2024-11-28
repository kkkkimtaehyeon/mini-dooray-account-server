package com.nhnacademy.accountserver.service;

import com.nhnacademy.accountserver.dtos.AccountResponseDto;
import com.nhnacademy.accountserver.dtos.AccountSaveRequestDto;
import com.nhnacademy.accountserver.dtos.AccountLoginRequest;
import com.nhnacademy.accountserver.entity.Account;
import com.nhnacademy.accountserver.entity.Member;
import com.nhnacademy.accountserver.exception.MemberAccountNotFoundException;
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

    public Account getAccount(long memberId) {
        return accountRepository.findByMember_MemberId(memberId).orElseThrow(() -> new MemberAccountNotFoundException("찾을 수 없는 계정입니다."));
    }

    //반환 dto로 바꿔야됨
    public Account updatePassword(Account account, String password) {
        return accountRepository.save(new Account(account.getAccountId(), account.getId(), passwordEncoder.encode(password), account.getMember()));
    }

    public AccountResponseDto login(AccountLoginRequest loginRequest) {

        AccountResponseDto accountResponseDto =  accountRepository.findById((loginRequest.getId()));

        if (!passwordEncoder.matches(loginRequest.getPassword(), accountResponseDto.getPassword())) {
            throw new RuntimeException("Incorerect Password");
        }

        return accountResponseDto;
    }

}
