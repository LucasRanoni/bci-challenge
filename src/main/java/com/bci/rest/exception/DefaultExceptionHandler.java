package com.bci.rest.exception;

import com.bci.rest.dto.DefaultErrorResponse;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<DefaultErrorResponse> handle(ValidationException exception) {
        DefaultErrorResponse defaultErrorResponse = new DefaultErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
        return responseWithError(exception, defaultErrorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultErrorResponse> handleValidationExceptions(final MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        final DefaultErrorResponse defaultErrorResponse = new DefaultErrorResponse(HttpStatus.BAD_REQUEST, errors.toString());
        return responseWithError(exception, defaultErrorResponse);
    }

    @ExceptionHandler({EmailAlreadyExistsException.class, EmailException.class})
    public ResponseEntity<DefaultErrorResponse> handle(RuntimeException exception) {
        DefaultErrorResponse defaultErrorResponse = new DefaultErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
        return responseWithError(exception, defaultErrorResponse);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<DefaultErrorResponse> handle(UserNotFoundException exception) {
        DefaultErrorResponse defaultErrorResponse = new DefaultErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
        return responseWithError(exception, defaultErrorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body("The body must be of type json");
    }

    private ResponseEntity<DefaultErrorResponse> responseWithError(Throwable exception, DefaultErrorResponse defaultErrorResponse) {
        LOGGER.error(exception.getMessage(), exception);
        return new ResponseEntity<>(defaultErrorResponse, defaultErrorResponse.getStatus());
    }
}
