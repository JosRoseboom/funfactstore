package com.easingyou.funfactstore.fact;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
public class Purchase extends BaseEntity{

	@Getter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private FunFact funFact;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private AppUser buyer;

	@Column(nullable = false)
	private ZonedDateTime purchaseDate;

	public ZonedDateTime getPurchaseDateCurrentZone() {
		return purchaseDate.withZoneSameInstant(ZoneId.systemDefault());
	}

	// JPA
	protected Purchase() {}

	public Purchase(FunFact funFact, AppUser buyer) {
		this.funFact = funFact;
		this.buyer = buyer;
		this.purchaseDate = ZonedDateTime.now();
	}
}
