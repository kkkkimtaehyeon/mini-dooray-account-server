package com.nhnacademy.accountserver.dtos;

import com.nhnacademy.accountserver.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberUpdateRequestDto {
    private String id;
    private String password;
    private String email;

    public MemberUpdateRequestDto(String id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }
}
