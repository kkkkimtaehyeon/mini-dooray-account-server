package com.nhnacademy.accountserver.dtos;

import com.nhnacademy.accountserver.entity.Account;
import lombok.Data;

@Data
public class MemberSaveRequestDto {
    private String id;
    private String password;
    private String email;
}
