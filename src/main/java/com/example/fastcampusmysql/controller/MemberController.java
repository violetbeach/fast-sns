package com.example.fastcampusmysql.controller;

import com.example.fastcampusmysql.domain.member.dto.RegisterMemberRequest;
import com.example.fastcampusmysql.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {
    private final MemberWriteService memberWriteService;

    @PostMapping
    public void register(@RequestBody RegisterMemberRequest request) {
        memberWriteService.create(request);
    }

}
