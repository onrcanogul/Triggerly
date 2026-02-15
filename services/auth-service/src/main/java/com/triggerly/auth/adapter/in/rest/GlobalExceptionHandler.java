package com.triggerly.auth.adapter.in.rest;

import com.triggerly.auth.domain.exception.InvalidCredentialsException;
import com.triggerly.auth.domain.exception.UserAlreadyExistsException;
import com.triggerly.auth.domain.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import triggerly.common.response.ServiceResponse;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ServiceResponse<Void>> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ServiceResponse.error(List.of(ex.getMessage()), HttpStatus.CONFLICT.value()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ServiceResponse<Void>> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ServiceResponse.error(List.of(ex.getMessage()), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ServiceResponse<Void>> handleInvalidCredentials(InvalidCredentialsException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ServiceResponse.error(List.of(ex.getMessage()), HttpStatus.UNAUTHORIZED.value()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ServiceResponse<Void>> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ServiceResponse.error(List.of(ex.getMessage()), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ServiceResponse<Void>> handleGenericException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ServiceResponse.error(List.of("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
}

