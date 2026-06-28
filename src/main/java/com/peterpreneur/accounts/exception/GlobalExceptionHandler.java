package com.peterpreneur.accounts.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleAccountNotFound(
            AccountNotFoundException ex,
            WebRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiErrorResponse.builder()
                        .timestamp(Instant.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .error("Not Found")
                        .message(ex.getMessage())
                        .path(((ServletWebRequest) request).getRequest().getRequestURI())
                        .build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleBadRequest(
            IllegalArgumentException ex,
            WebRequest request) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ApiErrorResponse.builder()
                        .timestamp(Instant.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error("Bad Request")
                        .message(ex.getMessage())
                        .path(((ServletWebRequest) request).getRequest().getRequestURI())
                        .build());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleMalformedJson(
            HttpMessageNotReadableException ex,
            WebRequest request) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ApiErrorResponse.builder()
                        .timestamp(Instant.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error("Bad Request")
                        .message("Malformed or invalid request body")
                        .path(((ServletWebRequest) request).getRequest().getRequestURI())
                        .build());
    }

}
