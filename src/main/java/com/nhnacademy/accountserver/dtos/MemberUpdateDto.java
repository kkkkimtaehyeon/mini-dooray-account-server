package com.nhnacademy.accountserver.dtos;

import lombok.Data;

@Data
public class MemberUpdateDto {
    private String id;
    private String password;
    private String email;
}
