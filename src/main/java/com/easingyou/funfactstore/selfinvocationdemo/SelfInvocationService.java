package com.easingyou.funfactstore.selfinvocationdemo;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
class SelfInvocationService {

	private final SelfInvocationService myselfAsBean;

	SelfInvocationService(@Lazy SelfInvocationService myselfAsBean) {
		this.myselfAsBean = myselfAsBean;
	}

	boolean isInTransactionSelfInvocation(){
		return isInTransaction();
	}

	@Transactional
	boolean isInTransaction(){
		return TransactionSynchronizationManager.isActualTransactionActive();
	}

	boolean isInTransactionProxyCall(){
		return myselfAsBean.isInTransaction();
	}
}
