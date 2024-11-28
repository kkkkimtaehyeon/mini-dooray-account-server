package com.nhnacademy.accountserver.exception;

public class AccountAlreadyExistException extends RuntimeException {
    public AccountAlreadyExistException(String id) {
        super(String.format("%s는 이미 등록된 아이디입니다.", id));
    }
}
