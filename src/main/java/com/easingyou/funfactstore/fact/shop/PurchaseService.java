package com.easingyou.funfactstore.fact.shop;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easingyou.funfactstore.fact.AppUser;
import com.easingyou.funfactstore.fact.AppUserRepo;
import com.easingyou.funfactstore.fact.FunFact;
import com.easingyou.funfactstore.fact.FunFactRepo;
import com.easingyou.funfactstore.fact.Purchase;

@Service
class PurchaseService {

	private final PurchaseRepo purchaseRepo;
	private final AppUserRepo appUserRepo;
	private final FunFactRepo funFactRepo;

	PurchaseService(PurchaseRepo purchaseRepo, AppUserRepo appUserRepo, FunFactRepo funFactRepo) {
		this.purchaseRepo = purchaseRepo;
		this.appUserRepo = appUserRepo;
		this.funFactRepo = funFactRepo;
	}

	@Transactional
	LocalDateTime purchaseFunFact(String username) {
		AppUser buyer = appUserRepo.findByUsername(username).orElseThrow();
		final List<Long> alreadyPurchasedFFIds = buyer.getPurchases().stream()
				.map(Purchase::getFunFact)
				.map(FunFact::getId)
				.toList();
		FunFact funFact = funFactRepo.findFirstByIdNotIn(alreadyPurchasedFFIds)
				.orElseThrow(() -> new RuntimeException("Give this legend its money back: no more fun facts to purchase"));

		final Purchase purchase = new Purchase(funFact, buyer);
		purchaseRepo.save(purchase);

		return purchase.getPurchaseDate();
	}
}
