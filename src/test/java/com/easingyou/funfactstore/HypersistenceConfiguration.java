package com.easingyou.funfactstore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.hypersistence.optimizer.HypersistenceOptimizer;
import io.hypersistence.optimizer.core.config.JpaConfig;
import jakarta.persistence.EntityManagerFactory;

@Configuration
public class HypersistenceConfiguration {

    @Bean
    public HypersistenceOptimizer hypersistenceOptimizer(
            EntityManagerFactory entityManagerFactory) {
        return new HypersistenceOptimizer(
                new JpaConfig(
                        entityManagerFactory
                )
        );
    }
}
