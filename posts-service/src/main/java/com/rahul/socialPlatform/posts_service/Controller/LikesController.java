package com.rahul.socialPlatform.posts_service.Controller;

import com.rahul.socialPlatform.posts_service.Auth.UserContextHolder;
import com.rahul.socialPlatform.posts_service.Service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikesController {

    private final PostLikeService postLikeService;

    @PostMapping("/{postId}")
    public ResponseEntity<Void> likePosts(@PathVariable Long postId){

        Long userId = UserContextHolder.getCurrentUserId();

        postLikeService.likePost(postId , userId);

        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> unLikePosts(@PathVariable Long postId){

        Long userId = UserContextHolder.getCurrentUserId();

        postLikeService.unLikePost(postId , userId);

        return ResponseEntity.noContent().build();

    }

}
