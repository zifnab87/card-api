package com.cardapi.cardapi.controllers;

import com.cardapi.cardapi.AbstractIntegrationTest;
import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.repositories.CountryCostRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class CountryCostControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CountryCostRepo countryCostRepo;


    @BeforeEach
    public void setUp() {
        countryCostRepo.save(new CountryCost("EG", BigDecimal.valueOf(12.5)));
        countryCostRepo.save(new CountryCost("Others", BigDecimal.valueOf(15)));
    }

    @AfterEach
    public void tearDown() {
        countryCostRepo.deleteAll();
    }


    @Test
    public void create_country_cost_endpoint_works() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/country-costs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"country\": \"US\", \"cost\": 10}"))
                .andExpect(status().isOk());
    }

    @Test
    public void update_country_cost_endpoint_works() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/country-costs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"country\": \"EG\", \"cost\": 10}"))
                .andExpect(status().isOk());
    }

    @Test
    public void retrieve_all_country_costs_endpoint_works() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/country-costs"))
                .andExpect(status().isOk());
    }

    @Test
    public void  retrieve_cost_by_country_endpoint_works() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/country-costs/EG"))
                .andExpect(status().isOk());
    }

    @Test
    public void delete_cost_by_country_endpoint_works() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/country-costs/EG"))
                .andExpect(status().isOk());
    }
}