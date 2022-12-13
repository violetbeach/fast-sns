package com.example.fastcampusmysql.domain.post.dto;

public record PostRequest(
        long memberId,
        String contents
) {
}
