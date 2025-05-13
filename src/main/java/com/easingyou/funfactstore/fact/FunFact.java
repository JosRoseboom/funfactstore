package com.easingyou.funfactstore.fact;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
public class FunFact extends BaseEntity {

	@Getter
	private String fact;

	@Getter
	private String explanation;

	@Getter
	@ManyToOne
	@JoinColumn(nullable = false)
	private AppUser admin;
}
