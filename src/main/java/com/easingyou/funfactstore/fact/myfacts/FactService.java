package com.easingyou.funfactstore.fact.myfacts;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easingyou.funfactstore.fact.PurchaseRepo;

@Service
@Transactional(readOnly = true)
class FactService {

	private final PurchaseRepo purchaseRepo;

	FactService(PurchaseRepo purchaseRepo) {
		this.purchaseRepo = purchaseRepo;
	}

	List<FactDTO> findMyFacts(String username) {
		return purchaseRepo.findByBuyer_Username(username).stream()
				.map(purchase -> new FactDTO(purchase.getPurchaseDateCurrentZone(), purchase.getFunFact().getFact(), purchase.getFunFact().getAdmin().getEmail()))
				.toList();
	}
}
