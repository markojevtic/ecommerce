package com.prodyna.ecommerce.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = NOT_FOUND, reason = "Entity not found")
public class EntityNotFound extends RuntimeException {

	public EntityNotFound() {
		super();
	}

	public EntityNotFound(String message) {
		super(message);
	}
}
