package com.easingyou.funfactstore.fact;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;

@Entity
public class AppUser extends BaseEntity{

	@Column(nullable = false, unique = true)
	private String username;

	@Getter
	@Column(nullable = false, unique = true)
	private String email;

	@Getter
	@OneToMany(mappedBy = "buyer")
	private List<Purchase> purchases;

	public String getTopLevelDomain() {

		String domainPart = email.substring(email.indexOf('@') + 1);
		int lastDotIndex = domainPart.lastIndexOf('.');

		return domainPart.substring(lastDotIndex + 1).toLowerCase();
	}


}
