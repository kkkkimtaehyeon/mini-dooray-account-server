package com.nhnacademy.accountserver.controller;

import com.nhnacademy.accountserver.dtos.MemberSaveRequestDto;
import com.nhnacademy.accountserver.dtos.MemberUpdateRequestDto;
import com.nhnacademy.accountserver.entity.Member;
import com.nhnacademy.accountserver.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberAccountController {

    private final MemberService memberService;

    @PostMapping("/members")
    public void createMember(MemberSaveRequestDto requestDto) {

    }

    @PutMapping("/members/{memberId}")
    public ResponseEntity<Member> modifyMember(@PathVariable long memberId,
                                               MemberUpdateRequestDto updateRequestDto) {
        memberService.updateMember(memberId, updateRequestDto);
        return ResponseEntity.ok().build();
    }
}
