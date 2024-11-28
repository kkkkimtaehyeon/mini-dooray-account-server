package com.nhnacademy.accountserver.respository;

import com.nhnacademy.accountserver.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByMember_MemberId(long memberId);
    boolean existsById(String id);
}
