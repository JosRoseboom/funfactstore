package com.easingyou.funfactstore.fact.myfacts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import com.easingyou.funfactstore.TestcontainersConfiguration;

@Import(TestcontainersConfiguration.class)
@ActiveProfiles("test")
@DataJpaTest
class EqualsHashcodeToStringTest {

	@Autowired
	private MyPurchasesRepo myPurchasesRepo;

	@Test
	void testHashcodeReference(){
		final int hashCodeRef = myPurchasesRepo.getReferenceById(5L).hashCode();
		final int hashCodeInstantiated = myPurchasesRepo.findById(5L).orElseThrow().hashCode();
		assertEquals(hashCodeRef, hashCodeInstantiated);
	}

	@Test
	void testEqualsRef(){
		assertEquals(myPurchasesRepo.getReferenceById(1L), myPurchasesRepo.getReferenceById(1L));
	}

	@Test
	void testEquals(){
		assertEquals(myPurchasesRepo.findById(1L).orElseThrow(), myPurchasesRepo.getReferenceById(1L));
	}

	@Test
	void testNotEquals(){
		assertNotEquals(myPurchasesRepo.findById(1L).orElseThrow(), myPurchasesRepo.getReferenceById(2L));
	}

	@Test
	void testToString(){
		assertEquals("Purchase[id=2]", myPurchasesRepo.getReferenceById(2L).toString());
	}
}
