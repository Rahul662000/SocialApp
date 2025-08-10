package com.rahul.socialPlatform.posts_service.Controller;

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

        postLikeService.likePost(postId , 1L);

        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> unLikePosts(@PathVariable Long postId){

        postLikeService.unLikePost(postId , 1L);

        return ResponseEntity.noContent().build();

    }

}
