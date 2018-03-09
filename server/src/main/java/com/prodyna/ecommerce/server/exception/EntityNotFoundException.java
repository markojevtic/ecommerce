package com.prodyna.ecommerce.server.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "System could not find %s with id %s.";

    private EntityNotFoundException(String message) {
        super(message);
    }

    public static EntityNotFoundException createExceptionByEntityAndId(Class entityClass, Object id) {
        return new EntityNotFoundException(String.format(MESSAGE_TEMPLATE, entityClass.getName(), id));
    }
}
