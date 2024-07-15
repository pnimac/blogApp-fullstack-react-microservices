package com.pnimac.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pnimac.comment.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	
	List<Comment> findAllByOrderByCreatedAtAsc();
	
	List<Comment> findByPostId(Long postId);
		
}
