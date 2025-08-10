package com.rahul.socialPlatform.posts_service.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Long Id;
    private String content;
    private Long userId;
    private LocalDateTime createdAt;

}
