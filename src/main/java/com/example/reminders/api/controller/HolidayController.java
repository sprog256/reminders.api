package com.example.reminders.api.controller;

import com.example.reminders.api.data.Holiday;
import com.example.reminders.api.data.Reminder;
import com.example.reminders.api.dto.HolidayReadDto;
import com.example.reminders.api.dto.ReminderReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("holidays")
public class HolidayController {
    private final ConversionService conversionService;

    @GetMapping("{year}/{country}")
    @ResponseBody
    public ResponseEntity<List<HolidayReadDto>> holidays(
        @PathVariable("year")
        int year,
        @PathVariable("country")
        String country
    ) {
        RestClient restClient = RestClient.builder()
            .baseUrl("https://date.nager.at/api/v3/PublicHolidays")
            .build();
        ResponseEntity<List<Holiday>> responseEntity = restClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/" + year)
                .path("/" + country)
                .build())
            .header("Content-Type", "application/json")
            .retrieve()
            .toEntity(new ParameterizedTypeReference<>() {});

        var body = responseEntity.getBody();
        Objects.requireNonNull(body);
        List<HolidayReadDto> dto = convertList(body);
        return ResponseEntity.ok(dto);
    }

    private List<HolidayReadDto> convertList(List<Holiday> list) {
        return list.stream()
            .map(o -> conversionService.convert(o, HolidayReadDto.class))
            .toList();
    }
}
