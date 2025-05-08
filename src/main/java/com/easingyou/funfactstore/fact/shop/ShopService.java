package com.easingyou.funfactstore.fact.shop;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easingyou.funfactstore.fact.AppUser;
import com.easingyou.funfactstore.fact.AppUserRepo;
import com.easingyou.funfactstore.fact.FunFact;
import com.easingyou.funfactstore.fact.FunFactRepo;
import com.easingyou.funfactstore.fact.Purchase;
import com.easingyou.funfactstore.fact.PurchaseRepo;

@Service
@Transactional(readOnly = true)
class ShopService {
	private final PurchaseRepo purchaseRepo;
	private final AppUserRepo appUserRepo;
	private final FunFactRepo funFactRepo;

	ShopService(PurchaseRepo purchaseRepo, AppUserRepo appUserRepo, FunFactRepo funFactRepo) {
		this.purchaseRepo = purchaseRepo;
		this.appUserRepo = appUserRepo;
		this.funFactRepo = funFactRepo;
	}

	Optional<ZonedDateTime> getLastPurchaseDate(String username) {

		// Find the last purchase date
		return purchaseRepo.findByBuyer_Username(username).stream()
				.map(Purchase::getPurchaseDateCurrentZone)
				.max(Comparator.naturalOrder());
	}

	@Transactional
	ZonedDateTime purchaseFunFact(String username) {
		AppUser buyer = appUserRepo.findByUsername(username).orElseThrow();
		final List<Long> alreadyPurchasedFFIds = buyer.getPurchases().stream()
				.map(Purchase::getFunFact)
				.map(FunFact::getId)
				.toList();
		FunFact funFact = funFactRepo.findFirstByIdNotIn(alreadyPurchasedFFIds)
				.orElseThrow(() -> new RuntimeException("Give this legend its money back: no more fun facts to purchase"));

		final Purchase purchase = new Purchase(funFact, buyer);
		purchaseRepo.save(purchase);

		return purchase.getPurchaseDateCurrentZone();
	}
}
