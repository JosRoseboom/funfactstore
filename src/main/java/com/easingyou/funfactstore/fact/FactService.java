package com.easingyou.funfactstore.fact;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
class FactService {

	private final FactRepo factRepo;

	FactService(FactRepo factRepo) {
		this.factRepo = factRepo;
	}

	long count() {
		return factRepo.count();
	}


}
