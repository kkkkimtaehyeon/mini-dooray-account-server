package com.nhnacademy.accountserver.respository;

import com.nhnacademy.accountserver.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
