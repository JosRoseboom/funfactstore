package com.easingyou.funfactstore.fact.shop;

import org.springframework.stereotype.Service;

@Service
class ExternalAPI {

	boolean isThisACriminal(String identifier){

		try {
			Thread.sleep(200L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		return false;
	}
}
