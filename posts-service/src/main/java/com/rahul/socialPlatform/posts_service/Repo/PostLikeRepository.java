package com.rahul.socialPlatform.posts_service.Repo;

import com.rahul.socialPlatform.posts_service.Entity.PostLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PostLikeRepository extends JpaRepository<PostLikeEntity , Long> {

    boolean existsByUserIdAndPostId(Long userId , Long postId);

    @Transactional
    void deleteByUserIdAndPostId(Long userId, Long postId);
}
