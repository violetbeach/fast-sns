package com.example.fastcampusmysql.application.controller;

import com.example.fastcampusmysql.application.usecase.CreatePostUseCase;
import com.example.fastcampusmysql.application.usecase.GetTimelinePostUseCase;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.dto.PostRequest;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.service.PostReadService;
import com.example.fastcampusmysql.domain.post.service.PostWriteService;
import com.example.fastcampusmysql.util.CursorRequest;
import com.example.fastcampusmysql.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostReadService postReadService;

    private final GetTimelinePostUseCase getTimelinePostUseCase;
    private final CreatePostUseCase createPostUseCase;

    @PostMapping
    public Long create(PostRequest request) {
        return createPostUseCase.execute(request);
    }

    @GetMapping("/daily-post-counts")
    public List<DailyPostCount> getDailyPostCounts(@RequestBody DailyPostCountRequest request) {
        return postReadService.getDailyPostCounts(request);
    }

    @GetMapping("members/{memberId}")
    public PageCursor<Post> getPosts(
            @PathVariable Long memberId,
            CursorRequest cursorRequest
    ) {
        return postReadService.getPosts(memberId, cursorRequest);
    }

    @GetMapping("/members/{memberId}/timeline")
    public PageCursor<Post> getTimeline(
            @PathVariable Long memberId,
            CursorRequest cursorRequest
    ) {
        return getTimelinePostUseCase.executeByTimeline(memberId, cursorRequest);
    }
}
