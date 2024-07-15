package com.pnimac.auth.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.pnimac.auth.model.User;

public class UserDTO {
	private Long id;
	private String username;
	private String email;
    private Collection<? extends GrantedAuthority> roles;

	public UserDTO() {}
	
	public UserDTO(Long id, String username, String email, Collection<? extends GrantedAuthority> roles) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<? extends GrantedAuthority> getRoles() {
		return roles;
	}

	public void setRoles(Collection<? extends GrantedAuthority> roles) {
		this.roles = roles;
	}

	public static UserDTO fromEntity(User user) {
		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setEmail(user.getEmail());
		dto.setRoles(null);
		return dto;
	}
	

}
