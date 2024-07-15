package com.pnimac.auth.model;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public String rolename;
	
	@Override
	public String getAuthority() {
		return rolename;
	}

}
