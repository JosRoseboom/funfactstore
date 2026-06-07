package com.easingyou.funfactstore.selfinvocationdemo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
class SelfInvocationService {

	boolean isInTransactionSelfInvocation(){
		return isInTransaction();
	}

	@Transactional
	boolean isInTransaction(){
		return TransactionSynchronizationManager.isActualTransactionActive();
	}
}
