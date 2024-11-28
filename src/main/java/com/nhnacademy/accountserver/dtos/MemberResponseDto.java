package com.nhnacademy.accountserver.dtos;

import com.nhnacademy.accountserver.enums.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class MemberResponseDto {
    private String id;
    private String email;
    private MemberStatus status;
}
