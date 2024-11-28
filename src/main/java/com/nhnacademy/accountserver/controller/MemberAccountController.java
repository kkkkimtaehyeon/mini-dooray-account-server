package com.nhnacademy.accountserver.controller;

import com.nhnacademy.accountserver.dtos.MemberSaveRequestDto;
import com.nhnacademy.accountserver.dtos.MemberUpdateDto;
import com.nhnacademy.accountserver.entity.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberAccountController {

    @PostMapping("/members")
    public void createMember(MemberSaveRequestDto requestDto) {

    }

    @PostMapping("/members/{memberId}")
    public ResponseEntity<Member> modifyMember(MemberUpdateDto updateDto) {
        return ResponseEntity.ok().build();
    }
}
