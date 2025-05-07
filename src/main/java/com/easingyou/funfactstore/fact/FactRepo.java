package com.easingyou.funfactstore.fact;

import org.springframework.data.jpa.repository.JpaRepository;

interface FactRepo extends JpaRepository<FunFact, Long> {
}
