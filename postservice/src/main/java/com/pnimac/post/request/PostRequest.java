package com.pnimac.post.request;

public class PostRequest {

	private String title;
	private String body;
	private String username;
	
	public PostRequest() {}
	
	public PostRequest(String title, String body, String username) {
		this.title = title;
		this.body = body;
		this.username = username;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
