package com.nhnacademy.accountserver.dtos;

import com.nhnacademy.accountserver.entity.Account;
import com.nhnacademy.accountserver.entity.Member;
import lombok.Getter;

@Getter
public class AccountSaveRequestDto {
    private String id;
    private String password;

    public Account toEntity(Member member) {
        return new Account(this.id, this.password, member);
    }
}
