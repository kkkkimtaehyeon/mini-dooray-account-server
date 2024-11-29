package com.nhnacademy.accountserver.dtos;

import com.nhnacademy.accountserver.entity.Account;
import com.nhnacademy.accountserver.entity.Member;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class AccountUpdateRequestDto {
    private Long accountId;
    private String id;
    private String password;
    private Member member;

    public AccountUpdateRequestDto(Account account) {
        this.accountId = account.getAccountId();
        this.id = account.getId();
        this.password = account.getPassword();
        this.member = account.getMember();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account toEntity() {
        return new Account(accountId, id, password, member);
    }
}