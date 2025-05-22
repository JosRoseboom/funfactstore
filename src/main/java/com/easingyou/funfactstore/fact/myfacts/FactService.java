package com.easingyou.funfactstore.fact.myfacts;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easingyou.funfactstore.fact.Purchase;

@Service
@Transactional(readOnly = true)
class FactService {
	private final Logger log = LoggerFactory.getLogger(FactService.class);
	private final MyPurchasesRepo myPurchasesRepo;

	FactService(MyPurchasesRepo myPurchasesRepo) {
		this.myPurchasesRepo = myPurchasesRepo;
	}

	List<FactDTO> findMyFacts(String username) {
		log.debug("Finding facts for user {}", username);

		final List<Purchase> puchasesForUser = myPurchasesRepo.findByBuyer_Username(username);

		log.debug("Found {} facts for user {}", puchasesForUser.size(), username);

		final List<FactDTO> factDTOS = puchasesForUser.stream()
				.map(purchase -> new FactDTO(purchase.getPurchaseDate(), purchase.getFunFact().getFact(), purchase.getFunFact().getAdmin().getEmail()))
				.toList();

		log.debug("Mapped entities to dtos. Returning {}", factDTOS);
		return factDTOS;
	}
}
