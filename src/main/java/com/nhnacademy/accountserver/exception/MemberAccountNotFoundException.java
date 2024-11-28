package com.nhnacademy.accountserver.exception;

public class MemberAccountNotFoundException extends RuntimeException {
    public MemberAccountNotFoundException() {
        super("찾을 수 없는 회원 계정입니다.");
    }
}
