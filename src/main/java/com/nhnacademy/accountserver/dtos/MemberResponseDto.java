package com.nhnacademy.accountserver.dtos;

import com.nhnacademy.accountserver.entity.Member;
import com.nhnacademy.accountserver.enums.MemberStatus;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private long id;
    private String email;
    private MemberStatus memberStatus;

    public MemberResponseDto(Member member) {
        this.id = member.getMemberId();
        this.email = member.getEmail();
        this.memberStatus = member.getMemberStatus();
    }
}
