package com.easingyou.funfactstore.fact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FactControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FactService factService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        FactController controller = new FactController(factService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

}
