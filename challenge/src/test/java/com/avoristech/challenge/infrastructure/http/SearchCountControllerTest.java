package com.avoristech.challenge.infrastructure.http;

import com.avoristech.challenge.EnableTestcontainers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableTestcontainers
@SpringBootTest
@AutoConfigureMockMvc
class SearchCountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void retrieve() throws Exception {
        mockMvc.perform(
                get("/count/{searchId}", "019109d4-2c3b-7743-b287-d1d67756e674")
        ).andExpect(status().isOk());
    }
}