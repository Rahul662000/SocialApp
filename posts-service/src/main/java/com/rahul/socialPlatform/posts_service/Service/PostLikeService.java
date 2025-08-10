package com.rahul.socialPlatform.posts_service.Service;

import com.rahul.socialPlatform.posts_service.Entity.PostLikeEntity;
import com.rahul.socialPlatform.posts_service.Exception.BadRequestException;
import com.rahul.socialPlatform.posts_service.Exception.ResourceNotFoundException;
import com.rahul.socialPlatform.posts_service.Repo.PostLikeRepository;
import com.rahul.socialPlatform.posts_service.Repo.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    public void likePost(Long postId , Long userId){

        log.info("Attempting to like the post with id : {}" , postId);

        boolean exists = postRepository.existsById(postId);

        if(!exists){
            throw new ResourceNotFoundException("Post not found with id : {}" + postId);
        }

        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId , postId);

        if(alreadyLiked)
            throw new BadRequestException("Cannot like the same post again.");

        PostLikeEntity postLikeEntity = new PostLikeEntity();

        postLikeEntity.setPostId(postId);
        postLikeEntity.setUserId(userId);
        postLikeRepository.save(postLikeEntity);

        log.info("Post with id : {} liked successfully" , postId);

    }

    public void unLikePost(Long postId, Long userId) {

        log.info("Attempting to unlike the post with id : {}" , postId);

        boolean exists = postRepository.existsById(postId);

        if(!exists){
            throw new ResourceNotFoundException("Post not found with id : {}" + postId);
        }

        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId , postId);

        if(!alreadyLiked)
            throw new BadRequestException("Cannot unlike the same post again.");

        postLikeRepository.deleteByUserIdAndPostId(userId , postId);


        log.info("Post with id : {} unliked successfully" , postId);

    }
}
