package com.example.reminders.api.service;

import com.example.reminders.api.convert.ReminderConverter;
import com.example.reminders.api.convert.ReminderCreateDtoConverter;
import com.example.reminders.api.convert.ReminderUpdateDtoConverter;
import com.example.reminders.api.data.Reminder;
import com.example.reminders.api.dto.ReminderCreateDto;
import com.example.reminders.api.dto.ReminderReadDto;
import com.example.reminders.api.dto.ReminderUpdateDto;
import com.example.reminders.api.error.EntityNotFoundByIdException;
import com.example.reminders.api.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReminderService {
    private final ReminderRepository reminderRepository;
    private final ReminderConverter reminderConverter;
    private final ReminderCreateDtoConverter reminderCreateDtoConverter;
    private final ReminderUpdateDtoConverter reminderUpdateDtoConverter;

    public List<ReminderReadDto> findAll() {
        var reminders = reminderRepository.findAllByOrderByDeadlineAsc();
        return reminderConverter.convert(reminders);
    }

    public List<ReminderReadDto> findAllByDay(LocalDate day) {
        var startDeadline = day.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        var endDeadline = day.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant();
        var reminders = reminderRepository.findAllByDeadlineBetweenOrderByDeadlineAsc(
            startDeadline,
            endDeadline
        );
        return reminderConverter.convert(reminders);
    }

    @Transactional
    public ReminderReadDto create(ReminderCreateDto dto) {
        var reminder = reminderCreateDtoConverter.convert(dto);

        var savedReminder = reminderRepository.save(reminder);
        log.info("Created reminder {}", savedReminder.getId());

        return reminderConverter.convert(savedReminder);
    }

    @Transactional
    public ReminderReadDto update(ReminderUpdateDto dto, Long id) {
        var reminderNew = reminderUpdateDtoConverter.convert(dto);

        var reminder = find(id);
        reminder.setDeadline(reminderNew.getDeadline());
        reminder.setDescription(reminderNew.getDescription());
        var savedReminder = reminderRepository.save(reminder);
        log.info("Updated reminder {}", savedReminder.getId());

        return reminderConverter.convert(savedReminder);
    }

    @Transactional
    public void delete(Long id) {
        var reminder = find(id);
        reminderRepository.delete(reminder);
        log.info("Deleted reminder {}", reminder.getId());
    }

    private Reminder find(Long id) {
        return reminderRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundByIdException("reminders", id));
    }
}
