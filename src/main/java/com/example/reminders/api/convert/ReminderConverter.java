package com.example.reminders.api.convert;

import com.example.reminders.api.data.Reminder;
import com.example.reminders.api.dto.ReminderReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.TimeZone;

@RequiredArgsConstructor
@Component
public class ReminderConverter {
    private final LocalConverter localConverter;

    public ReminderReadDto convert(Reminder source) {

        return ReminderReadDto.builder()
            .id(source.getId())
            .deadline(source.getDeadline())
            .description(source.getDescription())
            .build();
    }

    public List<ReminderReadDto> convert(List<Reminder> source) {
        return source.stream()
            .map(this::convert)
            .toList();
    }
}
