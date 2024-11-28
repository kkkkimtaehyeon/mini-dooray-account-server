package com.nhnacademy.accountserver.controller;

import com.nhnacademy.accountserver.dtos.MemberSaveRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberAccountController {

    @PostMapping("/members")
    public void createMember(MemberSaveRequestDto requestDto) {

    }
}
