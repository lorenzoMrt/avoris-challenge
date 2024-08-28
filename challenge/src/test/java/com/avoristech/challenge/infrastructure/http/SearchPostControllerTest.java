package com.avoristech.challenge.infrastructure.http;

import com.avoristech.challenge.EnableTestcontainers;
import com.avoristech.challenge.infrastructure.http.req.SearchReq;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableTestcontainers
@SpringBootTest
@AutoConfigureMockMvc
class SearchPostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void create_a_valid_search() throws Exception {
        var body = new SearchReq(
                "123aBc",
                LocalDate.of(2024, 12, 29),
                LocalDate.of(2024, 12, 31),
                List.of(30, 29, 1, 3));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String jsonBody = objectMapper.writeValueAsString(body);

        mockMvc.perform(
                request(HttpMethod.POST, "/search").content(jsonBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    void create_search_with_invalid_hotelId() throws Exception {
        var body = new SearchReq(
                "12345",
                LocalDate.of(2023, 12, 29),
                LocalDate.of(2023, 12, 31),
                List.of(30, 29, 1, 3));

        performInvalidRequest(body, "hotelId","Hotel ID must contain at least one uppercase letter, one lowercase letter, and one digit");
    }

    @Test
    void create_search_with_null_checkIn() throws Exception {
        var body = new SearchReq(
                "123aBc",
                null,
                LocalDate.of(2023, 12, 31),
                List.of(30, 29, 1, 3));

        performInvalidRequest(body, "checkIn","Check-in date cannot be null");
    }

    @Test
    void create_search_with_past_checkIn() throws Exception {
        var body = new SearchReq(
                "123aBc",
                LocalDate.of(2022, 12, 29),
                LocalDate.of(2023, 12, 31),
                List.of(30, 29, 1, 3));

        performInvalidRequest(body, "checkIn","Check-in date must be today or in the future");
    }

    @Test
    void create_search_with_null_checkOut() throws Exception {
        var body = new SearchReq(
                "123aBc",
                LocalDate.of(2023, 12, 29),
                null,
                List.of(30, 29, 1, 3));

        performInvalidRequest(body, "checkOut","Check-out date cannot be null");
    }

    @Test
    void create_search_with_empty_ages() throws Exception {
        var body = new SearchReq(
                "123aBc",
                LocalDate.of(2023, 12, 29),
                LocalDate.of(2023, 12, 31),
                List.of());

        performInvalidRequest(body, "ages","Ages list cannot be empty");
    }

    @Test
    void create_search_with_too_many_ages() throws Exception {
        var body = new SearchReq(
                "123aBc",
                LocalDate.of(2023, 12, 29),
                LocalDate.of(2023, 12, 31),
                List.of(30, 29, 1, 3, 4, 5, 6, 7, 8, 9, 10));

        performInvalidRequest(body, "ages","Ages list cannot have more than 10 entries");
    }

    @Test
    void create_search_with_negative_age() throws Exception {
        var body = new SearchReq(
                "123aBc",
                LocalDate.of(2023, 12, 29),
                LocalDate.of(2023, 12, 31),
                List.of(30, -1, 1, 3));

        performInvalidRequest(body, "ages[1]","Age cannot be negative");
    }

    @Test
    void create_search_with_too_high_age() throws Exception {
        var body = new SearchReq(
                "123aBc",
                LocalDate.of(2023, 12, 29),
                LocalDate.of(2023, 12, 31),
                List.of(30, 121, 1, 3));

        performInvalidRequest(body,"ages[1]" ,"Age cannot be greater than 120");
    }

    private void performInvalidRequest(SearchReq body,String fieldName, String errorMessage) throws Exception {
        mockMvc.perform(
                        request(HttpMethod.POST, "/search")
                                .content(asJsonString(body))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(String.format("{\"%s\":\"%s\"}",fieldName, errorMessage)));
    }

    private String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}