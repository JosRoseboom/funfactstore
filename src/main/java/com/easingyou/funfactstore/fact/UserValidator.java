package com.easingyou.funfactstore.fact;

import org.springframework.stereotype.Service;

@Service
class UserValidator {

	boolean isThisACriminal(long userId){

		try {
			Thread.sleep(100L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		return false;
	}
}
