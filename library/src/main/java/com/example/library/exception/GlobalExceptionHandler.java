package com.example.library.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> handleNotFound(BookNotFoundException ex) {
        return ResponseEntity.status(404).body(new ErrorResponse("NOT_FOUND", ex.getMessage()));
    }

    // error response DTO
    static class ErrorResponse {
        private final String error;
        private final String message;
        public ErrorResponse(String error, String message) {
            this.error = error;
            this.message = message;
        }
        public String getError() { return error; }
        public String getMessage() { return message; }
    }
}

