package com.nhnacademy.accountserver.dtos;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountLoginRequest {
    private String id;
    private String password;
}
