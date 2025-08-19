package com.rahul.socialPlatform.posts_service.Controller;

import com.rahul.socialPlatform.posts_service.Auth.UserContextHolder;
import com.rahul.socialPlatform.posts_service.Dto.PostCreateRequestDto;
import com.rahul.socialPlatform.posts_service.Dto.PostDto;
import com.rahul.socialPlatform.posts_service.Entity.PostEntity;
import com.rahul.socialPlatform.posts_service.Service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto , HttpServletRequest httpServletRequest){

        Long userId = UserContextHolder.getCurrentUserId();

        PostDto createdPost = postService.createPost(postCreateRequestDto , userId);

        return new ResponseEntity<>(createdPost , HttpStatus.CREATED);

    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long postId){
        PostDto post = postService.getPostById(postId);
        return ResponseEntity.ok(post);

    }

    @GetMapping("/users/{userId}/allposts")
    public ResponseEntity<List<PostDto>> getAllPostsOfUser(@PathVariable Long userId){

        List<PostDto> posts = postService.getAllPostsByUserId(userId);

        return ResponseEntity.ok(posts);

    }

}
