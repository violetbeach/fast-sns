package com.example.fastcampusmysql.application.controller;

import com.example.fastcampusmysql.application.usecase.CreatePostLikeUseCase;
import com.example.fastcampusmysql.application.usecase.CreatePostUseCase;
import com.example.fastcampusmysql.application.usecase.GetTimelinePostUseCase;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.dto.PostDto;
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
    private final PostWriteService postWriteService;

    private final CreatePostLikeUseCase createPostLikeUseCase;

    @PostMapping
    public Long create(PostRequest request) {
        return createPostUseCase.execute(request);
    }

    @GetMapping("/daily-post-counts")
    public List<DailyPostCount> getDailyPostCounts(@RequestBody DailyPostCountRequest request) {
        return postReadService.getDailyPostCounts(request);
    }

    @GetMapping("members/{memberId}")
    public PageCursor<PostDto> getPosts(
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

    @PostMapping("/{postId}/like")
    public void likePost(@PathVariable Long postId) {
        postWriteService.likePost(postId);
    }

    @PostMapping("/{postId}/like/v2")
    public void likePostV2(@PathVariable Long postId, @RequestParam Long memberId) {
        createPostLikeUseCase.execute(postId, memberId );
    }
}
