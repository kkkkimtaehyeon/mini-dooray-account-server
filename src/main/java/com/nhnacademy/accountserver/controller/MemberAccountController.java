package com.nhnacademy.accountserver.controller;

import com.nhnacademy.accountserver.dtos.MemberResponseDto;
import com.nhnacademy.accountserver.dtos.MemberSaveRequestDto;
import com.nhnacademy.accountserver.enums.MemberStatus;
import com.nhnacademy.accountserver.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberAccountController {

    private final MemberService memberService;

    @PostMapping("/members")
    public MemberResponseDto createMember(@Valid @RequestBody MemberSaveRequestDto memberSaveRequestDto) {
        return memberService.createMember(memberSaveRequestDto);
    }

    @PutMapping("/members/{memberId}/quit")
    public MemberResponseDto changeQuit(@PathVariable Long memberId) {
        return memberService.updateMemberStatus(memberId, MemberStatus.WITHDRAW);
    }

    @PutMapping("/members/{memberId}/sleep")
    public MemberResponseDto changeSleep(@PathVariable Long memberId) {
        return memberService.updateMemberStatus(memberId, MemberStatus.SLEEPER);
    }
}
