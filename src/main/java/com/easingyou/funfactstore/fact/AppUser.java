package com.easingyou.funfactstore.fact;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class AppUser extends BaseEntity{

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false, unique = true)
	private String email;

	public String getTopLevelDomain() {

		String domainPart = email.substring(email.indexOf('@') + 1);
		int lastDotIndex = domainPart.lastIndexOf('.');

		return domainPart.substring(lastDotIndex + 1).toLowerCase();
	}

	public String getEmail() {
		return email;
	}
}
