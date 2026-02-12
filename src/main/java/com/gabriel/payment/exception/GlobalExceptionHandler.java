package com.gabriel.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice       //intercep execptions from all the application
public class GlobalExceptionHandler {

    //capture any businessExcpetion launch
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex){


        //Exeption JAVA -> RESSPONSE HTTP
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.CONFLICT.value()
        );

        //CONVERT TO JSON
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }
}

