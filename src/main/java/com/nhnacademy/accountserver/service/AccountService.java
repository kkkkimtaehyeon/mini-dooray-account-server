package com.nhnacademy.accountserver.service;

import com.nhnacademy.accountserver.dtos.AccountSaveRequestDto;
import com.nhnacademy.accountserver.dtos.AccountUpdateRequestDto;
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
    //반환 dto로 바꿔야됨 found account, 회원 조회, member
    public void updatePassword(AccountUpdateRequestDto updateRequestDto, String password) {
//        Account account = accountRepository.findByMember_MemberId(updateRequestDto.accountId()).orElseThrow(() -> new MemberAccountNotFoundException(""));
//        accountRepository.save(new Account(account.getAccountId(), account.getId(), passwordEncoder.encode(password), account.getMember()));
//
//        accountRepository.save(new Account(updateRequestDto.accountId(), updateRequestDto.id(), passwordEncoder.encode(password), updateRequestDto.member()));

        accountRepository.save(updateRequestDto.toEntity());
    }
}
