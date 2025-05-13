package com.easingyou.funfactstore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.hypersistence.optimizer.HypersistenceOptimizer;
import io.hypersistence.optimizer.core.event.Event;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(classes = FunFactStoreApplication.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class NoPerformanceIssuesTest {

    @Autowired
    private HypersistenceOptimizer hypersistenceOptimizer;

    @Test
    public void testNoPerformanceIssues() {

        long nonMinors = hypersistenceOptimizer.getEvents()
            .stream()
            .filter(event -> Event.Priority.MINOR != event.getPriority())
            .count();

        assertEquals(0, nonMinors, "There are non minor performance issues");
    }
}
