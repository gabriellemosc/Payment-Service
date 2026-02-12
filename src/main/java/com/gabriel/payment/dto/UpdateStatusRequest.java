package com.gabriel.payment.dto;
import com.gabriel.payment.domain.TransactionStatus;
import jakarta.validation.constraints.NotNull;

public class UpdateStatusRequest {

    @NotNull(message = "Status is Mandatory")
    private TransactionStatus status;

    public TransactionStatus getStatus(){
        return status;
    }       //return status send

    public void setStatus(TransactionStatus status){
        this.status = status;
    }
}
