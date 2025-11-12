package com.example.reminders.api.error;

public class EntityNotFoundByIdException extends AppException {
    public EntityNotFoundByIdException(String entityName, Long id) {
        super("Nerastas įrašas.", String.format("Nerastas įrašas: %s, id %s.", entityName, id));
    }
}
