package com.easingyou.funfactstore.fact.myfacts;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.easingyou.funfactstore.FunFactStoreApplication;
import com.easingyou.funfactstore.TestcontainersConfiguration;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(classes = FunFactStoreApplication.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class FactControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void factstoreEndpoint_withUsername_shouldReturnOk() throws Exception {
        // When making a GET request to / with username=mj
        // Then the response status should be 200 OK
        mockMvc.perform(get("/")
                .param("username", "mj"))
                .andExpect(status().isOk());
    }
}
