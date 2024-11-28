package com.nhnacademy.accountserver.dtos;

import com.nhnacademy.accountserver.enums.MemberStatus;

public record MemberResponseDto (long id, String email, MemberStatus memberStatus) {
}
