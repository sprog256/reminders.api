package com.example.reminders.api.convert;

import com.example.reminders.api.data.Reminder;
import com.example.reminders.api.dto.ReminderCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@RequiredArgsConstructor
@Component
public class ReminderCreateDtoConverter {

    public Reminder convert(ReminderCreateDto source) {
        return Reminder.builder()
            .deadline(source.getDeadline())
            .description(source.getDescription())
            .build();
    }
}
