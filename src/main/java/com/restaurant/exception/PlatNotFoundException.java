package com.restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlatNotFoundException extends RuntimeException {
    public PlatNotFoundException(Long id) {
        super("Plat introuvable avec l id : " + id);
    }
}
