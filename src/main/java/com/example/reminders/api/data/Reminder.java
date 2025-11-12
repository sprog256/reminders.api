package com.example.reminders.api.data;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "reminders")
@Entity
public class Reminder {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reminder_id")
    @Id
    Long id;

    @Column(name = "deadline", nullable = false)
    Instant deadline;

    @Column(name = "description", nullable = false, length = 2000)
    String description;
}
