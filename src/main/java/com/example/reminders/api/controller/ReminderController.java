package com.example.reminders.api.controller;

import com.example.reminders.api.convert.LocalConverter;
import com.example.reminders.api.dto.ReminderCreateDto;
import com.example.reminders.api.dto.ReminderReadDto;
import com.example.reminders.api.dto.ReminderUpdateDto;
import com.example.reminders.api.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.TimeZone;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("reminders")
public class ReminderController {
    private final ReminderService reminderService;

    @PostMapping
    public ReminderReadDto create(@RequestBody ReminderCreateDto dto) {
        return reminderService.create(dto);
    }

    @GetMapping
    @ResponseBody
    public List<ReminderReadDto> readAll() {
        return reminderService.findAll();
    }

    @GetMapping("{day}")
    @ResponseBody
    public List<ReminderReadDto> readByDay(@PathVariable("day") LocalDate day) {
        return reminderService.findAllByDay(day);
    }

    @PutMapping("{id}")
    public ReminderReadDto update(@RequestBody ReminderUpdateDto dto, @PathVariable("id") Long id) {
        return reminderService.update(dto, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        reminderService.delete(id);
    }

}
