package com.gabriel.payment.exception;
//RETURN HTTP 400 instead of GENERIC server error 500

public class BusinessException extends RuntimeException{

    public BusinessException(String message){
        super(message); //send to runtime excpetion
    }
}
