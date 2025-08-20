package com.rahul.socialPlatform.post_service.Events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreatedEvents {

    Long creatorId;
    String content;
    Long postId;

}
