package com.nhnacademy.accountserver.respository;

import com.nhnacademy.accountserver.dtos.AccountResponseDto;
import com.nhnacademy.accountserver.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByMember_MemberId(long memberId);
    AccountResponseDto findById(String id);
    boolean existsById(String id);

}
