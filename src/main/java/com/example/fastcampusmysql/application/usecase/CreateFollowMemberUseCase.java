package com.example.fastcampusmysql.application.usecase;

import com.example.fastcampusmysql.domain.follow.service.FollowWriteService;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateFollowMemberUseCase {

    private final MemberReadService memberReadService;
    private final FollowWriteService followWriteService;

    /*
    * [Target]
    *   - 1. 입력받은 memberId로 회원 조회
    *   - 2. FollowWriteService.create()
    * */
    public void execute(long fromMemberId, long toMemberId) {
        var fromMember = memberReadService.getMember(fromMemberId);
        var toMember = memberReadService.getMember(toMemberId);

        followWriteService.create(fromMember, toMember);
    }
}
