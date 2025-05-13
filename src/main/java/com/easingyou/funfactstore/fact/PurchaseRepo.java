package com.easingyou.funfactstore.fact;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PurchaseRepo extends JpaRepository<Purchase, Long> {

	List<Purchase> findByBuyer_Username(String username);

	@Query("""
			select max(p.purchaseDate) from Purchase p
				join p.buyer buyer
				where buyer.username = :username
	""")
	Optional<ZonedDateTime> findLastPurchaseDateByBuyer_Username(String username);
}
