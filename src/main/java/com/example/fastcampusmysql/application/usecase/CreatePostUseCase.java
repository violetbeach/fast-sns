package com.example.fastcampusmysql.application.usecase;

import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.service.FollowReadService;
import com.example.fastcampusmysql.domain.post.dto.PostRequest;
import com.example.fastcampusmysql.domain.post.service.PostWriteService;
import com.example.fastcampusmysql.domain.post.service.TimelineWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatePostUseCase {
    private final PostWriteService postWriteService;
    private final FollowReadService followReadService;
    private final TimelineWriteService timelineWriteService;

    public Long execute(PostRequest postRequest) {
        var postId = postWriteService.create(postRequest);

        var followMemberIds = followReadService.getFollowers(postRequest.memberId())
                .stream()
                .map(Follow::getFromMemberId)
                .toList();

        timelineWriteService.deliveryToTimeline(postId, followMemberIds);

        return postId;
    }
}
