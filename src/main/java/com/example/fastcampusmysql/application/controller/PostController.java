package com.example.fastcampusmysql.application.controller;

import com.example.fastcampusmysql.domain.post.dto.PostRequest;
import com.example.fastcampusmysql.domain.post.service.PostWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostWriteService postWriteService;

    @PostMapping
    public Long create(PostRequest request) {
        return postWriteService.create(request);
    }
}
