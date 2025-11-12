package com.example.reminders.api.convert;

import com.example.reminders.api.data.Holiday;
import com.example.reminders.api.dto.HolidayReadDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class HolidayConverter implements Converter<Holiday, HolidayReadDto> {
    @Override
    public HolidayReadDto convert(Holiday source) {
        return HolidayReadDto.builder()
            .day(source.getDate())
            .name(source.getLocalName())
            .build();
    }
}
