package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.RegisterMemberRequest;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import com.example.fastcampusmysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberWriteService {

    private final MemberRepository memberRepository;
    private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    /*
    * [Target]
    *   - 회원 정보(이메일, 닉네임, 생년월일)를 등록한다.
    *   - 닉네임은 10자를 넘길 수 없다.
    * 파라미터 - memberRegisterRequest
    *
    * val member = Member.of(memberRegisterRequest)
    * memberRepository.save(member)
    */
    @Transactional
    public Member register(RegisterMemberRequest request) {
        Member member = Member.builder()
                .nickname(request.nickname())
                .email(request.email())
                .birthday(request.birthday())
                .build();
        Member savedMember = memberRepository.save(member);
        saveNicknameHistory(savedMember);
        return savedMember;
    }

    /*
    * [Target]
    *   - 회원의 이름을 변경한다.
    *   - 변경 내역을 저장한다.
    * */
    public void changeNickname(Long memberId, String nickname) {
        var member = memberRepository.findById(memberId).get();
        member.changeNickname(nickname);
        memberRepository.save(member);

        saveNicknameHistory(member);
    }

    private void saveNicknameHistory(Member member) {
        var history = MemberNicknameHistory.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();
        memberNicknameHistoryRepository.save(history);
    }

}
