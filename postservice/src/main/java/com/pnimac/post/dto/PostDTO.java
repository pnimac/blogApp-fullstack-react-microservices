package com.pnimac.post.dto;

import java.util.Date;

import com.pnimac.post.model.Post;

public class PostDTO {

	private Long id;
	private String title;
	private String content;
	private String username;
	private Date createdAt;

	public PostDTO() {
	}

	public PostDTO(Long postid, String title, String content, String username, Date createdAt) {
		super();
		this.id = postid;
		this.title = title;
		this.content = content;
		this.username = username;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public static PostDTO fromEntity(Post post) {
		PostDTO postDTO = new PostDTO();
		postDTO.setId(post.getId());
		postDTO.setTitle(post.getTitle());
		postDTO.setContent(post.getContent());
		postDTO.setUsername(post.getUsername());
		postDTO.setCreatedAt(post.getCreatedAt());
		return postDTO;
	}

}
