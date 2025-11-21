package dev.indy.lifelink.core;

import dev.indy.lifelink.exception.HttpException;
import dev.indy.lifelink.model.response.DetailedErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DetailedErrorResponse> handleValidationExceptions(MethodArgumentNotValidException e, HttpServletRequest request) {
        Map<String, List<String>> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            String field = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();

            errors.computeIfAbsent(field, k -> new java.util.ArrayList<>()).add(errorMessage);
        });

        return new ResponseEntity<>(new DetailedErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Bad Request",
            request.getRequestURI(),
            errors
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DetailedErrorResponse> handleHttpMessageNotReadableExceptions(HttpMessageNotReadableException e, HttpServletRequest request) {
        return new ResponseEntity<>(new DetailedErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Bad Request",
            request.getRequestURI(),
            e.getMessage()
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<DetailedErrorResponse> handleIllegalArgumentExceptions(IllegalArgumentException e, HttpServletRequest request) {
        return new ResponseEntity<>(new DetailedErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Bad Request",
            request.getRequestURI(),
            e.getMessage()
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<DetailedErrorResponse> handleHttpExceptions(HttpException e, HttpServletRequest request) {
        HttpStatus status = e.getStatus();

        return new ResponseEntity<>(new DetailedErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            request.getRequestURI(),
            e.getMessage()
        ), status);
    }
}
