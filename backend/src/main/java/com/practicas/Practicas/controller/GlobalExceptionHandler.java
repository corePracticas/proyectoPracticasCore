package com.practicas.Practicas.controller;

import com.practicas.Practicas.model.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Get all validation errors
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation error", errors);

        return ResponseEntity.badRequest().body(response);
    }
}