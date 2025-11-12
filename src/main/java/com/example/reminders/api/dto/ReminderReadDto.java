package com.example.reminders.api.dto;

import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ReminderReadDto {
    Long id;
    Instant deadline;
    String description;
}
