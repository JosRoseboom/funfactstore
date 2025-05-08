package com.easingyou.funfactstore.fact.myfacts;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
