package com.example.reminders.api.repository;

import com.example.reminders.api.data.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    List<Reminder> findAllByOrderByDeadlineAsc();

    List<Reminder> findAllByDeadlineBetweenOrderByDeadlineAsc(
        Instant deadlineStart,
        Instant deadlineEnd
    );
}
