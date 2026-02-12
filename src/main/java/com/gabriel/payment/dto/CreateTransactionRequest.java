package com.gabriel.payment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

//FORMER -> USER CHECK AND THEN CHECL
public class CreateTransactionRequest {

    @NotNull(message = "Amount is mandatory")
    @Positive(message = "Amount must be bigger than zero")
    private BigDecimal amount;

    @NotBlank(message = "TransactionId is mandatory")
    private String transactionId;

    public BigDecimal getAmount(){
        return amount;
    }

    public String getTransactionId(){
        return transactionId;
    }
}
