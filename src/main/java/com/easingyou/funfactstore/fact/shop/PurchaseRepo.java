package com.easingyou.funfactstore.fact.shop;

import java.time.ZonedDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.easingyou.funfactstore.fact.Purchase;

interface PurchaseRepo extends JpaRepository<Purchase, Long> {

	@Query("""
			select max(p.purchaseDate) from Purchase p
				join p.buyer buyer
				where buyer.username = :username
	""")
	Optional<ZonedDateTime> findLastPurchaseDateByBuyer_Username(String username);
}
