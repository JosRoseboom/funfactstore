package com.easingyou.funfactstore.fact;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

interface AppUserRepo extends JpaRepository<AppUser, Long> {
	Optional<AppUser> findByUsername(String username);
}
