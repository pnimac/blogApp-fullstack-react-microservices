package com.pnimac.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pnimac.post.model.Post;
import com.pnimac.post.repository.PostRepository;

import jakarta.transaction.Transactional;

@Service
public class PostService {
	
	@Autowired
    private PostRepository postRepository;

	public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

	@Transactional
    public Post addPost(Post post) {
        return postRepository.saveAndFlush(post);
    }

	@Transactional
    public void deletePost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            postRepository.deleteById(postId);
        } else {
            throw new RuntimeException("Post not found.");
        }
    }
}
