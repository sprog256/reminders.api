package com.example.reminders.api.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.StringJoiner;
import java.util.UUID;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(EntityNotFoundByIdException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundByIdException(EntityNotFoundByIdException e) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(handle(e));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(handle(e));
    }

    private ErrorResponse handle(Exception e) {
        var errorId = UUID.randomUUID().toString();
        log.error(createLogMessage(e, errorId), e);
        return new ErrorResponse(createUserMessage(e, errorId));
    }

    private String createUserMessage(Exception e, String errorId) {
        var sj = new StringJoiner(" ");
        if (e instanceof AppException) {
            sj.add(((AppException) e).getSafeMessage());
        }
        sj.add(String.format("Klaida %.6s.", errorId));
        return sj.toString();
    }

    private String createLogMessage(Exception e, String errorId) {
        var sj = new StringJoiner(" ");
        if (e instanceof AppException) {
            sj.add(((AppException) e).getSafeMessage());
        }
        sj.add(String.format("Klaida %s.", errorId));
        return sj.toString();
    }
}
