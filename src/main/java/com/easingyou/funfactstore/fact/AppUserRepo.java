package com.easingyou.funfactstore.fact;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {
	Optional<AppUser> findByUsername(String username);

	@Query("""
			select au.email from AppUser au
			where au.username = :username
	""")
	Optional<String> findEmailByUsername(String username);
}
