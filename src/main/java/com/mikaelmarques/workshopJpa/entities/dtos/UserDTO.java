package com.mikaelmarques.workshopJpa.entities.dtos;

import com.mikaelmarques.workshopJpa.entities.User;

public class UserDTO {
	
	private Long id;
	
	private String name;
	
	public UserDTO() {}
	
	public UserDTO(User x) {
		this.id = x.getId();
		this.name = x.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
