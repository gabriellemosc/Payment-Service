package com.gabriel.payment.dto;

import java.math.BigDecimal;

//FORMER -> USER CHECK AND THEN CHECL
public class CreateTransactionRequest {

    private BigDecimal amount;
    private String transactionId;

    public BigDecimal getAmount(){
        return amount;
    }

    public String getTransactionId(){
        return transactionId;
    }
}
