package com.easingyou.funfactstore.fact.myfacts;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.easingyou.funfactstore.FunFactStoreApplication;
import com.easingyou.funfactstore.TestcontainersConfiguration;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(classes = FunFactStoreApplication.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class FactServiceIntegrationTest {

    @Autowired
    private FactService factService;

    @Test
    void findAllFactsWithExplanation_shouldReturnFactsWithExplanations() {
        assertNotNull(factService.findAllPurchaseDates());
    }
}
