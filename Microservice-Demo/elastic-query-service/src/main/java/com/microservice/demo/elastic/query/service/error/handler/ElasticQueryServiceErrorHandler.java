package com.microservice.demo.elastic.query.service.error.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ElasticQueryServiceErrorHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ElasticQueryServiceErrorHandler.class);


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handler(IllegalArgumentException e){
        LOG.error("IllegalArgumentException Exception!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("IllegalArgumentException: " + e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handler(RuntimeException e){
        LOG.error("Runtime Exception!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Service RunTime Exception: " + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handler(Exception e){
        LOG.error("Internal Server Exception!");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("A server error occurred: " + e.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handler(MethodArgumentNotValidException e){
        LOG.error("MethodArgumentNotValidException Exception!");
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error ->
                errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
