package gov.iti.career.hub.controllers.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handleValidationExceptions(
            ResponseStatusException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("timestamp", LocalDateTime.now().toString());
        errors.put("status",ex.getStatusCode().toString());
        errors.put("error",ex.getReason());
        return ResponseEntity.status(ex.getStatusCode()).body(errors);
    }
}
