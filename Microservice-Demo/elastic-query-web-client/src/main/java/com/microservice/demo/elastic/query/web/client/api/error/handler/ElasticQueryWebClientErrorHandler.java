package com.microservice.demo.elastic.query.web.client.api.error.handler;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ElasticQueryWebClientErrorHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticQueryWebClientErrorHandler.class);

//    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
//    public String handle(AccessDeniedException e, Model model) {
//        LOG.error("Access denied exception!");
//        model.addAttribute("error", HttpStatus.UNAUTHORIZED.getReasonPhrase());
//        model.addAttribute("error_description, You are not authorized to access this resource!");
//        return "error";
//    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handle(IllegalArgumentException e) {
        LOG.error("Illegal argument exception!", e);
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handle(Exception e) {
        LOG.error("Internal server error!", e);
        return "error";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handle(RuntimeException el) {
        LOG.error("Service runtime exception!", el);
        return "home";
    }

    @ExceptionHandler({BindException.class})
    public String handle(BindException e) {
        LOG.error("Method argument validation exception!", e);
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error ->
                errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
        return "home";
    }

}
