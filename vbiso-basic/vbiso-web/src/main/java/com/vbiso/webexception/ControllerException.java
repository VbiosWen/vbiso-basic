package com.vbiso.webexception;

import com.vbiso.result.ServiceResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("com.vbiso.controller")
public class ControllerException {

    @ExceptionHandler(Exception.class)
    public ServiceResult defaultException(){
        return new ServiceResult();
    }
}
