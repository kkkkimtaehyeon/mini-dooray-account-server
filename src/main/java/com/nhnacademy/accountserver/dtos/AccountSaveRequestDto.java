package com.nhnacademy.accountserver.dtos;

import com.nhnacademy.accountserver.entity.Account;
import com.nhnacademy.accountserver.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
public class AccountSaveRequestDto {
    private String id;
    @Setter
    private String password;

    public AccountSaveRequestDto(MemberSaveRequestDto requestDto) {
        this.id = requestDto.getId();
        this.password = requestDto.getPassword();
    }

    public Account toEntity(Member member) {
        return new Account(this.id, this.password, member);
    }
}
