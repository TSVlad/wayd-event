package com.example.waydevent.restapi.controller.advice;

import com.example.waydevent.restapi.controller.advice.exceptions.ForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler({ForbiddenException.class})
    ResponseEntity<HttpStatus> forbiddenExceptionHandle() {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
