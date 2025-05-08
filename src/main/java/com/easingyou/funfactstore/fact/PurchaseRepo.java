package com.easingyou.funfactstore.fact;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepo extends JpaRepository<Purchase, Long> {

	List<Purchase> findByBuyer_Username(String username);
}
