package com.example.fastcampusmysql.domain.member.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record RegisterMemberRequest(
        String email,
        String nickname,
        LocalDate birthday
) {
}
