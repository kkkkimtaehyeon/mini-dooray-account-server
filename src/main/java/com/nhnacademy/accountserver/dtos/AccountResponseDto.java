package com.nhnacademy.accountserver.dtos;

import com.nhnacademy.accountserver.entity.Account;
import com.nhnacademy.accountserver.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
public class AccountResponseDto {
    private String id;
    private String password;


    public AccountResponseDto(String id, String password) {
        this.id = id;
        this.password = password;

    }
}
