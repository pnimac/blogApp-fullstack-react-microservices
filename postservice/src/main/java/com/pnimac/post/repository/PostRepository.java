package com.pnimac.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pnimac.post.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findAllByOrderByCreatedAtDesc();

    List<Post> findByUsername(String username);
}

