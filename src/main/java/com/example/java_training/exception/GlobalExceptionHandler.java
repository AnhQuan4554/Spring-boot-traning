package com.example.java_training.exception;



import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex, HttpServletRequest request) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", ex.getReason());
        errorDetails.put("status", ex.getStatusCode().value());
        errorDetails.put("path", request.getRequestURI());
        errorDetails.put("method", request.getMethod());

        log.error("Error at {}: {}", request.getRequestURI(), ex.getReason());

        return ResponseEntity.status(ex.getStatusCode()).body(errorDetails);
    }
}
