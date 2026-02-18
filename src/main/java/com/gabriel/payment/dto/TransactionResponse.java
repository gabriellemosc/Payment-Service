package com.gabriel.payment.dto;
import com.gabriel.payment.domain.Transaction;
import com.gabriel.payment.domain.TransactionStatus;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//CLIENT RECEIVE AFTER THE OPERATION
public class TransactionResponse {

    private String id;
    private BigDecimal amount;
    private String transactionId;
    private TransactionStatus status;
    private LocalDateTime createdAt;


    public TransactionResponse(Transaction transaction){
        this.id = transaction.getId();                      //intern iD
        this.amount = transaction.getAmount();
        this.transactionId = transaction.getTransactionId(); //extern ID
        this.status = transaction.getStatus();
        this.createdAt = transaction.getCreatedAt();

    }

    public String getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
