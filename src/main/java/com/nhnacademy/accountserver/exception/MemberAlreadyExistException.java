package com.nhnacademy.accountserver.exception;

public class MemberAlreadyExistException extends RuntimeException {
    public MemberAlreadyExistException(String email) {
        super(String.format("%s는 이미 등록된 이메일입니다.", email));
    }
}
