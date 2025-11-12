package com.example.reminders.api.convert;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

@AllArgsConstructor
@Component
public class LocalConverter {
    public LocalDateTime toSystem(LocalDateTime client, TimeZone client_tz) {
        return client
            .atZone(client_tz.toZoneId())
            .toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
    }

    public LocalDateTime toClient(LocalDateTime system, TimeZone client_tz) {
        return system
            .atZone(ZoneId.systemDefault())
            .toInstant()
            .atZone(client_tz.toZoneId())
            .toLocalDateTime();
    }
}
