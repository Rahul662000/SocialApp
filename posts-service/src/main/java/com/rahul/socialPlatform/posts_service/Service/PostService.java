package com.rahul.socialPlatform.posts_service.Service;

import com.rahul.socialPlatform.posts_service.Dto.PostCreateRequestDto;
import com.rahul.socialPlatform.posts_service.Dto.PostDto;
import com.rahul.socialPlatform.posts_service.Entity.PostEntity;
import com.rahul.socialPlatform.posts_service.Exception.ResourceNotFoundException;
import com.rahul.socialPlatform.posts_service.Repo.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper mapper;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto , Long userId) {

        PostEntity post = mapper.map(postCreateRequestDto , PostEntity.class);

        post.setUserId(userId);

        PostEntity savedPost = postRepository.save(post);

        return mapper.map(savedPost , PostDto.class);

    }

    public PostDto getPostById(Long postId) {

        log.debug("Retrieving post with Id : {}" , postId);
        return mapper.map(postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post not found with id : " + postId)) , PostDto.class);

    }

    public List<PostDto> getAllPostsByUserId(Long userId) {
        List<PostEntity> posts = postRepository.findByUserId(userId);

        return posts
                .stream()
                .map((e) -> mapper.map(e , PostDto.class))
                .collect(Collectors.toList());
    }
}
