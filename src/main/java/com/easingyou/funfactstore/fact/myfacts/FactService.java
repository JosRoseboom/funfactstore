package com.easingyou.funfactstore.fact.myfacts;

import java.time.ZonedDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easingyou.funfactstore.fact.Purchase;
import com.easingyou.funfactstore.fact.PurchaseRepo;

@Service
@Transactional(readOnly = true)
class FactService {
	private final Logger log = LoggerFactory.getLogger(FactService.class);

	private final PurchaseRepo purchaseRepo;

	FactService(PurchaseRepo purchaseRepo) {
		this.purchaseRepo = purchaseRepo;
	}

	List<ZonedDateTime> findAllPurchaseDates() {
		log.info("Find all purchase dates");
		final List<Purchase> purchases = purchaseRepo.findAll();
		log.info("Found {} purchase dates", purchases.size());

		final List<ZonedDateTime> purchaseDates = purchases.stream()
				.map(Purchase::getPurchaseDateCurrentZone)
				.toList();

		log.info("Created purchase dates list");

		return purchaseDates;
	}

	List<FactDTO> findMyFacts(String username) {
		return purchaseRepo.findByBuyer_Username(username).stream()
				.map(purchase -> new FactDTO(purchase.getPurchaseDateCurrentZone(), purchase.getFunFact().getFact(), purchase.getFunFact().getAdmin().getEmail()))
				.toList();
	}
}
