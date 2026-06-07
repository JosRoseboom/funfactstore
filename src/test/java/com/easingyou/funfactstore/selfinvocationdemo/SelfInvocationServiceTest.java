package com.easingyou.funfactstore.selfinvocationdemo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import com.easingyou.funfactstore.FunFactStoreApplication;
import com.easingyou.funfactstore.TestcontainersConfiguration;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(classes = FunFactStoreApplication.class)
@ActiveProfiles("test")
class SelfInvocationServiceTest {

	@Autowired
	private SelfInvocationService selfInvocationService;

	@Test
	void isInTransactionSelfInvocation() {
		assertTrue(selfInvocationService.isInTransactionSelfInvocation());
	}

	@Test
	void isInTransaction() {
		assertTrue(selfInvocationService.isInTransaction());
	}
}
