package com.easingyou.funfactstore.fact;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class FactService {

	private final AppUserRepo appUserRepo;

	FactService(AppUserRepo appUserRepo) {
		this.appUserRepo = appUserRepo;
	}

	List<FactDTO> findMyFacts(String username) {
		AppUser user = appUserRepo.findByUsername(username).orElseThrow();

		return user.getPurchases().stream()
				.map(purchase -> new FactDTO(purchase.getPurchaseDate(), purchase.getFunFact().getFact(), purchase.getFunFact().getAdmin().getEmail()))
				.toList();

	}
}
