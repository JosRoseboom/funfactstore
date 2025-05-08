package com.easingyou.funfactstore.fact;

import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
class Purchase extends BaseEntity{

	@Getter
	@ManyToOne
	@JoinColumn(nullable = false)
	private FunFact funFact;

	@ManyToOne
	@JoinColumn(nullable = false)
	private AppUser buyer;

	@Getter
	@Column(nullable = false)
	private ZonedDateTime purchaseDate;
}
