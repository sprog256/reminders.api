package com.example.reminders.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class ReminderCreateDto {
    Instant deadline;
    String description;
}
