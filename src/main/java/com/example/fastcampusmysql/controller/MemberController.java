package com.example.fastcampusmysql.controller;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.dto.MemberNicknameHistoryDto;
import com.example.fastcampusmysql.domain.member.dto.RegisterMemberRequest;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;
import com.example.fastcampusmysql.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {
    private final MemberWriteService memberWriteService;
    private final MemberReadService memberReadService;

    @PostMapping
    public MemberDto register(@RequestBody RegisterMemberRequest request) {
        Member member = memberWriteService.register(request);
        return memberReadService.toDto(member);
    }

    @GetMapping("/{id}")
    public MemberDto findById(@PathVariable long id) {
        return memberReadService.getMember(id);
    }

    @PutMapping("/{id}/name")
    public MemberDto changeNickname(@PathVariable Long id, @RequestBody String nickname) {
        memberWriteService.changeNickname(id, nickname);
        return memberReadService.getMember(id);
    }

    @GetMapping("/{memberId}/nickname-histories")
    public List<MemberNicknameHistoryDto> getNicknameHistories(@PathVariable Long memberId) {
        return memberReadService.getNicknameHistories(memberId);
    }
}
