package com.pnimac.comment.dto;

import java.util.Date;

import com.pnimac.comment.model.Comment;

public class CommentDTO {

	private Long id;
	private String content;
	private Long postId;
	private String username;
	private Date createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public CommentDTO() {
	}

	public CommentDTO(Long id, String content, Long postId, String username, Date createdAt) {
		super();
		this.id = id;
		this.content = content;
		this.postId = postId;
		this.username = username;
		this.createdAt = createdAt;
	}

	public static CommentDTO fromEntity(Comment comment) {
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setId(comment.getId());
		commentDTO.setPostId(comment.getPostId());
		commentDTO.setContent(comment.getContent());
		commentDTO.setUsername(comment.getUsername());
		commentDTO.setCreatedAt(comment.getCreatedAt());
		return commentDTO;
	}
}
