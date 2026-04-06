package com.mikaelmarques.workshopJpa.enums;

public enum UserRolesEnum {
	
	ADMIN("admin"),
	
	USER("user");
	
	private String role;
	
	private UserRolesEnum(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
	
}
