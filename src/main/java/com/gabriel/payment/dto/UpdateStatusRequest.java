package com.gabriel.payment.dto;
import com.gabriel.payment.domain.TransactionStatus;
import jakarta.validation.constraints.NotNull;

public class UpdateStatusRequest {

    @NotNull(message = "Status is Mandatory")
    private TransactionStatus status;

    private String transactionId;

    public TransactionStatus getStatus(){
        return status;
    }       //return status send

    public void setStatus(TransactionStatus status){
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId; // retorna id
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId; // define id
    }
}
