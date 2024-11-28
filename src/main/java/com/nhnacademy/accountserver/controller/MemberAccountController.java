package com.nhnacademy.accountserver.controller;

import com.nhnacademy.accountserver.dtos.MemberResponseDto;
import com.nhnacademy.accountserver.dtos.MemberSaveRequestDto;
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

    @GetMapping("/members/{memberId}/quit")
    public MemberResponseDto changeQuit(@PathVariable Long memberId) {
        return memberService.updateMember(memberId);
    }
}
