package com.nhnacademy.accountserver.exception;

public class MemberAccountNotFoundException extends RuntimeException {
    public MemberAccountNotFoundException(String message) {
        super(message);
    }
}
