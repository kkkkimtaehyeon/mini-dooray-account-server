package com.nhnacademy.accountserver.respository;

import com.nhnacademy.accountserver.entity.Member;
import com.nhnacademy.accountserver.exception.MemberNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.nhnacademy.accountserver.enums.MemberStatus.ACTIVE;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    void beforeEach() {
        member = new Member(1L, "test@email.com123", ACTIVE);
        memberRepository.save(member);
    }

    @Test
    @DisplayName("email 존재하지 않는 경우")
    void NotExistsByEmail() {
        Assertions.assertFalse(memberRepository.existsByEmail("hi@h.com"));
    }

    @Test
    @DisplayName("email 존재하는 경우")
    void existsByEmail() {
        Assertions.assertTrue(memberRepository.existsByEmail(member.getEmail()));
    }

}