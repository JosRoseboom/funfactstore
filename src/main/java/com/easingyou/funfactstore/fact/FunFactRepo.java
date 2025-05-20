package com.easingyou.funfactstore.fact;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FunFactRepo extends JpaRepository<FunFact, Long> {

	Optional<FunFact> findFirstByIdNotIn(List<Long> factIds);

	@Query("""
		from FunFact ff join fetch ff.admin
	""")
	List<FunFact> findAllWithAdmin();
}
