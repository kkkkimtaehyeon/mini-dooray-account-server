package com.nhnacademy.accountserver.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException() {
        super("등록되지 않은 회원입니다.");
    }
}
