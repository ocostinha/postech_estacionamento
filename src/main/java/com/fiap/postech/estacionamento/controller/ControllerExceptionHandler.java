package com.fiap.postech.estacionamento.controller;

import com.fiap.postech.estacionamento.commoms.exception.ControllerNotFoundException;
import com.fiap.postech.estacionamento.commoms.exception.StandardError;
import com.fiap.postech.estacionamento.commoms.exception.UnprocessableEntityException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {
    private static final StandardError err = new StandardError();

    @ExceptionHandler(ControllerNotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public StandardError entityNotFound(
            ControllerNotFoundException e,
            HttpServletRequest request) {
        HttpStatus status = HttpStatus.NO_CONTENT;
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Entity not found");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        return err;
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public StandardError unprocessableEntity(
            UnprocessableEntityException e,
            HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Unprocessable Entity");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        return err;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });


        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Unprocessable Entity");
        err.setMessage(errors.toString());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
}
