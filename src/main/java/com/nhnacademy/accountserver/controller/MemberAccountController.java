package com.nhnacademy.accountserver.controller;

import com.nhnacademy.accountserver.dtos.*;
import com.nhnacademy.accountserver.enums.MemberStatus;
import com.nhnacademy.accountserver.service.AccountService;
import com.nhnacademy.accountserver.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberAccountController {

    private final MemberService memberService;
    private final AccountService accountService;

    @PostMapping("/members")
    public MemberResponseDto createMember(@Valid @RequestBody MemberSaveRequestDto memberSaveRequestDto) {
        return memberService.createMember(memberSaveRequestDto);
    }

    @GetMapping("/members/{memberId}")
    public MemberResponseDto getMember(@PathVariable("memberId") Long memberId) {
        return memberService.getMember(memberId);
    }

    @PutMapping("/members/{memberId}/{status}")
    public MemberResponseDto changeJoin(@PathVariable("memberId") Long memberId,
                                        @PathVariable("status") String status) {
        return switch (status) {
            case "quit" -> memberService.updateMemberStatus(memberId, MemberStatus.QUIT);
            case "sleep" -> memberService.updateMemberStatus(memberId, MemberStatus.SLEEPER);
            default -> memberService.updateMemberStatus(memberId, MemberStatus.ACTIVE);
        };
    }

    @PutMapping("/members/{memberId}")
    public MemberResponseDto updateMember(@PathVariable("memberId") Long memberId,
                                          @Valid @RequestBody MemberUpdateRequestDto requestDto) {
        return memberService.updateMember(memberId, requestDto);
    }

    @PostMapping("/login/process")
    public LoginResponseDto processLogin(HttpServletResponse response, @RequestBody AccountLoginRequest loginRequest)  {
        LoginResponseDto loginResponseDto = accountService.login(loginRequest);

        Cookie sessionCookie = new Cookie("LOGIN_SESSION", loginResponseDto.getSessionId());
        sessionCookie.setHttpOnly(true);
        sessionCookie.setMaxAge(3600);
        sessionCookie.setPath("/");
        response.addCookie(sessionCookie);

        return loginResponseDto;
    }
}
