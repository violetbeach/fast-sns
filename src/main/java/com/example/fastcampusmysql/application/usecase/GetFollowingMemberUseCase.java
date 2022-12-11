package com.example.fastcampusmysql.application.usecase;

import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.service.FollowReadService;
import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GetFollowingMemberUseCase {

    private final MemberReadService memberReadService;
    private final FollowReadService followReadService;

    /*
    * [Target]
    *   - fromMemberId = memberId -> Follow List 조회
    *   - 1번을 순회하면서 회원 정보를 가져온다.
    * */
    public List<MemberDto> execute(long memberId) {
        var followings = followReadService.getFollowings(memberId)
                .stream()
                .map(Follow::getToMemberId)
                .toList();
        return memberReadService.getMembers(followings);
    }

}
