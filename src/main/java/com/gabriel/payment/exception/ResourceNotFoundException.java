package com.gabriel.payment.exception;

public class ResourceNotFoundException extends  RuntimeException{

    //receive the message of error
    public ResourceNotFoundException(String message){
        super(message);     //send to runtime excpetion
    }
}
