package com.example.reminders.api.controller;

import com.example.reminders.api.TestContainersConfiguration;
import com.example.reminders.api.dto.ReminderReadDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@Import(TestContainersConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReminderControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void create__success() throws Exception {
        // ARANGE
        var requestContent = """
            {
                "deadline": "2025-11-12T15:55:00Z",
                "description": "dummy A"
            }
        """;

        // ACT
        var mvcResult = mockMvc.perform(
            post("/reminders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestContent)
            )
            .andExpect(status().isOk())
            .andReturn();
        var responseContent = mvcResult.getResponse().getContentAsString();
        var dto = objectMapper.readValue(responseContent, ReminderReadDto.class);

        // ASSERT
        assertTrue(dto.getId() > 0);
        assertEquals("2025-11-12T15:55:00Z", dto.getDeadline().toString());
        assertEquals("dummy A", dto.getDescription());
    }

}
