package com.gabriel.payment.dto;
import com.gabriel.payment.domain.TransactionStatus;


public class WebhookRequest {

    private String eventId;

    private String transactionId;

    private TransactionStatus status;



    public String getEventId(){
        return eventId;
    }

    public String getTransactionId(){
        return transactionId;
    }

    public TransactionStatus getStatus(){
        return status;
    }



}
