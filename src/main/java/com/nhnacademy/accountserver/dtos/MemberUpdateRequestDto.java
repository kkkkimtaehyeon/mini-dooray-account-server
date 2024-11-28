package com.nhnacademy.accountserver.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MemberUpdateRequestDto {
    @Size(min = 6, max = 20)
    private String password;
    @Email
    private String email;

    public MemberUpdateRequestDto(String password, String email) {
        this.password = password;
        this.email = email;
    }
}
