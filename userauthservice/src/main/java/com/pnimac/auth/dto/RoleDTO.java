package com.pnimac.auth.dto;

public class RoleDTO {

	private Long id;
	private String rolename;
	
	public RoleDTO() {}
	
	public RoleDTO(Long id, String rolename) {
		super();
		this.id = id;
		this.rolename = rolename;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	
}

