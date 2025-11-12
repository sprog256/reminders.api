package com.example.reminders.api.convert;

import com.example.reminders.api.data.Reminder;
import com.example.reminders.api.dto.ReminderUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ReminderUpdateDtoConverter {

    public Reminder convert(ReminderUpdateDto source) {
        return Reminder.builder()
            .deadline(source.getDeadline())
            .description(source.getDescription())
            .build();
    }
}
