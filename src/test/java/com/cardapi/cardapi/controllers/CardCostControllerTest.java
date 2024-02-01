package com.cardapi.cardapi.controllers;

import com.cardapi.cardapi.AbstractIntegrationTest;
import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.repositories.CountryCostRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class CardCostControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CountryCostRepo countryCostRepo;


    @BeforeEach
    public void setUp() {
        countryCostRepo.save(new CountryCost("EG", BigDecimal.valueOf(12.5)));
        countryCostRepo.save(new CountryCost("Others", BigDecimal.valueOf(10)));
    }

    @AfterEach
    public void tearDown() {
        countryCostRepo.deleteAll();
    }



    @Test
    public void retrieve_card_cost_endpoint_works() throws Exception {
        String requestBody = "{\"card_number\": \"1234567890123456\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/payment-cards-cost")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }
}