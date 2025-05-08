package com.easingyou.funfactstore.fact;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;

@Entity
public class AppUser extends BaseEntity{

	private String username;
	@Getter
	private String email;

	@Getter
	@OneToMany(mappedBy = "buyer")
	private List<Purchase> purchases;


}
