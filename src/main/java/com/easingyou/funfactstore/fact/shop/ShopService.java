package com.easingyou.funfactstore.fact.shop;

import java.time.ZonedDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easingyou.funfactstore.fact.PurchaseRepo;

@Service
class ShopService {
	private final Logger log = LoggerFactory.getLogger(ShopService.class);

	private final PurchaseRepo purchaseRepo;
	private final ExternalAPI externalAPI;

	ShopService(PurchaseRepo purchaseRepo, ExternalAPI externalAPI) {
		this.purchaseRepo = purchaseRepo;
		this.externalAPI = externalAPI;
	}

	@Transactional
	Optional<ZonedDateTime> getLastPurchaseDate(String username) {

		log.info("Check if criminal");
		if (externalAPI.isThisACriminal(username)){
			throw new RuntimeException("Criminal entered the shop!!!!!");
		}

		log.info("User {} is not a criminal. Now look for last purchase date", username);

		// Find the last purchase date
		final Optional<ZonedDateTime> lastPurchase = purchaseRepo.findLastPurchaseDateByBuyer_Username(username);

		log.info("Last purchase date for user {} is {}", username, lastPurchase);
		return lastPurchase;
	}

}
