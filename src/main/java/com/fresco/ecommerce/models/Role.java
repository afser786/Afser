package com.fresco.ecommerce.models;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;

public enum Role {
	CONSUMER, SELLER;
}

class RoleGrantedAuthority implements GrantedAuthority {
	@Serial
	private static final long serialVersionUID = -3408298481881657796L;
	String role;

	public RoleGrantedAuthority(String role) {
		this.role = role;
	}

	@Override
	public String getAuthority() {
		return this.role;
	}

}