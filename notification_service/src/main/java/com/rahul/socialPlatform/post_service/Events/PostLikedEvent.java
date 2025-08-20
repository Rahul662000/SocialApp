package com.rahul.socialPlatform.post_service.Events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostLikedEvent {

    Long postId;
    Long creatorId;
    Long likedByUserId;

}
