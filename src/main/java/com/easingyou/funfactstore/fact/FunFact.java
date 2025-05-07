package com.easingyou.funfactstore.fact;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
class FunFact extends BaseEntity {

	private String fact;

	private String explanation;

	@ManyToOne
	@JoinColumn(nullable = false)
	private AppUser admin;
}
