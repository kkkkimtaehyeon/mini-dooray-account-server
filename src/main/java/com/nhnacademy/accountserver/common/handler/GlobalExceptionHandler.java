package com.nhnacademy.accountserver.common.handler;

import com.nhnacademy.accountserver.exception.MemberAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 회원 중복 예외처리
    @ExceptionHandler(MemberAlreadyExistException.class)
    public ResponseEntity<?> duplicatedResource(Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    // validation 실패 예외처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> invalidRequest(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()+"\n잘못된 입력값입니다.");
    }
}
