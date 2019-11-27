package com.frol.config;

import com.frol.model.exceptions.ClientUnregisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Component
public class RestErrorsHandler {

    @ExceptionHandler()
    @ResponseBody
    public void handle(Exception e) {
    }

    @ExceptionHandler(ClientUnregisteredException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handle(ClientUnregisteredException e) {
    }
}
