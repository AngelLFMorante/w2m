package com.w2m.app.application.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Global exception handler that catches and handles different types of exceptions
 * thrown by the application. This class provides specific handling for different
 * exception types and returns an appropriate HTTP response.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles general exceptions that are not caught by specific exception handlers.
     *
     * @param ex the exception that was thrown
     * @param request the current request
     * @return a {@link ResponseEntity} with a generic error message and HTTP status 500 (Internal Server Error)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles {@link NegativeIdException}, which is thrown when an invalid negative ID is used.
     *
     * @param ex the exception that was thrown
     * @param request the current request
     * @return a {@link ResponseEntity} with the exception message and HTTP status 400 (Bad Request)
     */
    @ExceptionHandler(NegativeIdException.class)
    public ResponseEntity<String> handleNegativeIdException(NegativeIdException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles {@link RuntimeException} exceptions that may occur during runtime.
     *
     * @param ex the exception that was thrown
     * @param request the current request
     * @return a {@link ResponseEntity} with the exception message and HTTP status 500 (Internal Server Error)
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles {@link IllegalArgumentException} exceptions, which typically indicate
     * an invalid argument was provided in the request.
     *
     * @param ex the exception that was thrown
     * @return a {@link ResponseEntity} with the exception message and HTTP status 400 (Bad Request)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles {@link EntityNotFoundException} exceptions, typically thrown when an entity
     * cannot be found in the database.
     *
     * @param ex the exception that was thrown
     * @return a {@link ResponseEntity} with the exception message and HTTP status 404 (Not Found)
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles {@link EmptyResultDataAccessException} exceptions, which occur when an expected
     * result is not found in the database.
     *
     * @param ex the exception that was thrown
     * @return a {@link ResponseEntity} with a message "Resource not found" and HTTP status 404 (Not Found)
     */
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        return new ResponseEntity<>("Recurso no encontrado", HttpStatus.NOT_FOUND);
    }
}
