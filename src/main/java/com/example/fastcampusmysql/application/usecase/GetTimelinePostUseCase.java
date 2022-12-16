package com.example.fastcampusmysql.application.usecase;

import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.service.FollowReadService;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.entity.Timeline;
import com.example.fastcampusmysql.domain.post.service.PostReadService;
import com.example.fastcampusmysql.domain.post.service.TimelineReadService;
import com.example.fastcampusmysql.util.CursorRequest;
import com.example.fastcampusmysql.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetTimelinePostUseCase {
    private final FollowReadService followReadService;
    private final PostReadService postReadService;
    private final TimelineReadService timelineReadService;

    public PageCursor<Post> execute(long memberId, CursorRequest cursorRequest) {
        var followings = followReadService.getFollowings(memberId);
        var followingMemberIds = followings.stream().map(Follow::getToMemberId).toList();

        return postReadService.getPosts(followingMemberIds, cursorRequest);
    }

    public PageCursor<Post> executeByTimeline(long memberId, CursorRequest cursorRequest) {
        var timelines = timelineReadService.getTimelines(memberId, cursorRequest);
        var postIds = timelines.body().stream().map(Timeline::getPostId).toList();

        return postReadService.getPosts(postIds, cursorRequest);
    }

}
