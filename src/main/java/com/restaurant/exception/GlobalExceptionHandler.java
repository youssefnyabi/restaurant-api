package com.restaurant.exception;

import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(PlatNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(PlatNotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(Map.of("message", ex.getMessage(), "status", 404));
    }
      
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
      
        ex.getBindingResult().getFieldErrors()
            .forEach(e -> fieldErrors.put(e.getField(), e.getDefaultMessage()));
        
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(Map.of(
                "message", "Donnees invalides",
                "status", 400,
                "erreurs", fieldErrors
            ));
    }
}
