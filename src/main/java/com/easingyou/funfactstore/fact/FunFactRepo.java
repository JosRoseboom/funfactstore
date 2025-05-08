package com.easingyou.funfactstore.fact;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FunFactRepo extends JpaRepository<FunFact, Long> {

	Optional<FunFact> findFirstByIdNotIn(List<Long> factIds);
}
