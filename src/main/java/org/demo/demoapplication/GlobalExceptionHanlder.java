package org.demo.demoapplication;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHanlder {

    @ExceptionHandler(CustomException.class)
    public static ResponseEntity<Object> customExceptionHandler(Exception e) {
        if(e instanceof CustomException) {
            System.out.println("innnnnnn");
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), ((CustomException) e).getCode());
        }
        return null;
    }
}
