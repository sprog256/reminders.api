package com.example.reminders.api.error;


import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
    private final String safeMessage;
    public AppException(String safeMessage, String fullMessage) {
        super(fullMessage);
        this.safeMessage = safeMessage;
    }
}
