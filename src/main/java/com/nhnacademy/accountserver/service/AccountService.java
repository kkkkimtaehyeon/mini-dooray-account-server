package com.nhnacademy.accountserver.service;

import com.nhnacademy.accountserver.dtos.AccountLoginRequest;
import com.nhnacademy.accountserver.dtos.AccountSaveRequestDto;
import com.nhnacademy.accountserver.dtos.AccountUpdateRequestDto;
import com.nhnacademy.accountserver.dtos.LoginResponseDto;
import com.nhnacademy.accountserver.entity.Account;
import com.nhnacademy.accountserver.entity.Member;
import com.nhnacademy.accountserver.exception.MemberAccountNotFoundException;
import com.nhnacademy.accountserver.exception.MemberAlreadyExistException;
import com.nhnacademy.accountserver.respository.AccountRepository;
import com.nhnacademy.accountserver.respository.LoginRedisUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoginRedisUtility loginRedisUtility;

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
        updateRequestDto.setPassword(passwordEncoder.encode(password));
        accountRepository.save(updateRequestDto.toEntity());
    }

    public LoginResponseDto login(AccountLoginRequest loginRequest) {
        Account account = accountRepository.findById(loginRequest.getId());

        if (!passwordEncoder.matches(loginRequest.getPassword(), account.getPassword())) {
            throw new RuntimeException("Incorrect Password");
        }

        // 입력받은 accountId로 회원 찾아서 회원에 일치하는 member 찾고
        Member member = account.getMember();

        // long memberId redis 저장
        return loginRedisUtility.loginRedisSave(member.getMemberId());
    }
}
