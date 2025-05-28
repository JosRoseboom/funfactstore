package com.easingyou.funfactstore.fact.myfacts;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easingyou.funfactstore.fact.FunFact;
import com.easingyou.funfactstore.fact.FunFactRepo;

@Service
@Transactional(readOnly = true)
class AdminFactService {

	private final Logger log = LoggerFactory.getLogger(AdminFactService.class);
	private final FunFactRepo funFactRepo;

	AdminFactService(FunFactRepo funFactRepo) {
		this.funFactRepo = funFactRepo;
	}

	@Transactional
	void sanitizeFacts() {
		log.info("fetch all facts");

		final List<FunFact> allFacts = funFactRepo.findAllWithAdmin();

		log.info("Fetched {} facts, now sanitize them", allFacts.size());

		allFacts.forEach(FunFact::sanitizeAdminBased);

		log.info("Sanitized");
	}
}
