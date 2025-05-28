package com.easingyou.funfactstore.fact.myfacts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.easingyou.funfactstore.FunFactStoreApplication;
import com.easingyou.funfactstore.TestcontainersConfiguration;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(classes = FunFactStoreApplication.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class AdminFactServiceTest {

    @Autowired
    private AdminFactService adminFactService;

    @Test
    void sanitize() {
        adminFactService.sanitizeFacts();
    }
}
