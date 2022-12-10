package com.example.fastcampusmysql.domain.member.dto;

import java.time.LocalDateTime;

public record MemberNicknameHistoryDto(
        Long id,
        long memberId,
        String nickname,
        LocalDateTime createdAt
) {
}
