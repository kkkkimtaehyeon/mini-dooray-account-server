package com.nhnacademy.accountserver.respository;

import com.nhnacademy.accountserver.dtos.AccountResponseDto;
import com.nhnacademy.accountserver.entity.Account;
import com.nhnacademy.accountserver.entity.Member;
import com.nhnacademy.accountserver.exception.MemberAccountNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static com.nhnacademy.accountserver.enums.MemberStatus.ACTIVE;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MemberRepository memberRepository;

    private Account account;
    private Member member;


    @BeforeEach
    void beforeEach() {
        member = new Member(1L, "test@email.com123", ACTIVE);
        memberRepository.save(member);

        account = new Account(1L, "testId5", "testPassword", member);
        accountRepository.save(account);
    }

    @Test
    @DisplayName("회원이 있는 경우")
    void findByMember_MemberId() {
        Optional<Account> account1 = accountRepository.findByMember_MemberId(member.getMemberId());

        Assertions.assertTrue(account1.isPresent());
        Assertions.assertEquals(account.getId(), account1.get().getId());
    }

    @Test
    @DisplayName("회원이 없는 경우")
    void notfoundByMember_MemberId() {
        Assertions.assertEquals(Optional.empty(), accountRepository.findByMember_MemberId(10L));
    }

    @Test
    @DisplayName("아이디로 AccountResponseDto 찾기")
    void findById() {
        AccountResponseDto dto = new AccountResponseDto(account.getId(), account.getPassword());

        // dto에 equalsAndHashCode 구현 필요 (equals -> 다른 객체로 인식)
        Assertions.assertEquals(dto.getId(), accountRepository.findById(account.getId()).getId());
    }

    @Test
    @DisplayName("회원이 존재하는 경우")
    void existsById() {
        Assertions.assertTrue(accountRepository.existsById(account.getId()));
    }
}