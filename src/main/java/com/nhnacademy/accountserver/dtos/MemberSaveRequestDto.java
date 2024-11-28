package com.nhnacademy.accountserver.dtos;

import com.nhnacademy.accountserver.entity.Member;
import com.nhnacademy.accountserver.enums.MemberStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberSaveRequestDto {
    @NotNull
    @Size(min = 6, max = 20)
    private String id;
    @Size(min = 6, max = 20)
    private String password;
    @Email
    private String email;

    public Member toEntity() {
        return new Member(this.email, MemberStatus.ACTIVE);
    }
}
