package com.easingyou.funfactstore.fact;

import jakarta.persistence.Entity;

@Entity
class AppUser extends BaseEntity{

	private String username;
	private String email;
}
