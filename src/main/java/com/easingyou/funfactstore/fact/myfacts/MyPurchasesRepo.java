package com.easingyou.funfactstore.fact.myfacts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easingyou.funfactstore.fact.Purchase;

interface MyPurchasesRepo extends JpaRepository<Purchase, Long> {

	List<Purchase> findByBuyer_Username(String username);

}
