package com.example.reminders.api.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record HolidayReadDto (
    LocalDate day,
    String name
) {

}
