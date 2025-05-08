package com.easingyou.funfactstore.fact.shop;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easingyou.funfactstore.fact.AppUser;
import com.easingyou.funfactstore.fact.AppUserRepo;
import com.easingyou.funfactstore.fact.Purchase;
import com.easingyou.funfactstore.fact.PurchaseRepo;

@Service
@Transactional(readOnly = true)
class ShopService {
	private final PurchaseRepo purchaseRepo;

	ShopService(PurchaseRepo purchaseRepo) {
		this.purchaseRepo = purchaseRepo;
	}

	Optional<ZonedDateTime> getLastPurchaseDate(String username) {

		// Find the last purchase date
		return purchaseRepo.findByBuyer_Username(username).stream()
				.map(Purchase::getPurchaseDate)
				.max(Comparator.naturalOrder());
	}

}
