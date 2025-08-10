package com.rahul.socialPlatform.posts_service.Repo;

import com.rahul.socialPlatform.posts_service.Entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity , Long> {


    List<PostEntity> findByUserId(Long userId);
}
