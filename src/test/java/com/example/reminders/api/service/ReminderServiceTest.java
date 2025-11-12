package com.example.reminders.api.service;

import com.example.reminders.api.convert.ReminderConverter;
import com.example.reminders.api.convert.ReminderCreateDtoConverter;
import com.example.reminders.api.data.Reminder;
import com.example.reminders.api.dto.ReminderCreateDto;
import com.example.reminders.api.dto.ReminderReadDto;
import com.example.reminders.api.repository.ReminderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
class ReminderServiceTest {
    @Mock
    private ReminderRepository reminderRepository;

    @Mock
    private ReminderConverter reminderConverter;

    @Mock
    private ReminderCreateDtoConverter reminderCreateDtoConverter;

    @InjectMocks
    private ReminderService reminderService;

    @Test
    void findAll__notEmpty() {
        // ARANGE
        List<Reminder> mockReminders = List.of(
            new Reminder(),
            new Reminder()
        );
        List<ReminderReadDto> mockDtos = List.of(
            new ReminderReadDto(),
            new ReminderReadDto()
        );
        when(reminderRepository.findAllByOrderByDeadlineAsc()).thenReturn(mockReminders);
        when(reminderConverter.convert(ArgumentMatchers.<Reminder>anyList())).thenReturn(mockDtos);

        // ACT
        List<ReminderReadDto> result = reminderService.findAll();

        // ASSERT
        assertEquals(2, result.size());
        verify(reminderRepository).findAllByOrderByDeadlineAsc();
        verify(reminderConverter).convert(ArgumentMatchers.<Reminder>anyList());
    }

    @Test
    void create__success() {
        // ARANGE
        var mockReminder = new Reminder();
        var mockReadDto = new ReminderReadDto(1L, Instant.now(), "dummy A");
        when(reminderCreateDtoConverter.convert(any(ReminderCreateDto.class))).thenReturn(mockReminder);
        when(reminderRepository.save(any(Reminder.class))).thenReturn(mockReminder);
        when(reminderConverter.convert(any(Reminder.class))).thenReturn(mockReadDto);

        // ACT
        ReminderReadDto result = reminderService.create(new ReminderCreateDto());

        // ASSERT
        assertEquals("dummy A", result.getDescription());
        verify(reminderCreateDtoConverter).convert(any(ReminderCreateDto.class));
        verify(reminderRepository).save(any(Reminder.class));
        verify(reminderConverter).convert(any(Reminder.class));
    }
}