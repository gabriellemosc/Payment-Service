package com.gabriel.payment.domain;
import com.gabriel.payment.exception.BusinessException;
import jakarta.persistence.*;   //JPA annoations
import java.math.BigDecimal;    //money
import java.time.LocalDateTime;


@Entity //will be a table on DB
@Table(name = "transactions")
public class Transaction {

    @Id //primary key
    @GeneratedValue(strategy =  GenerationType.UUID)
    private String id;  //unique identifier


    @Column(nullable = false, updatable = false)        // cant change the amount
    private BigDecimal amount;  //value of the payment

    @Enumerated(EnumType.STRING)    //if changes it doesnt broke
    @Column(nullable = false)
    private TransactionStatus status;
    @Column(nullable = false, unique = true)
    private String transactionId;

    @Column(nullable = false)   //obrigation date
    private LocalDateTime createdAt;

    public Transaction() {}

    public Transaction(BigDecimal amount, String transactionId){

        this.amount = amount;
        this.transactionId =  transactionId;
        this.status = TransactionStatus.CREATED;
        this.createdAt = LocalDateTime.now();

    }

    //final status that cannot be change
    private boolean isFinalStatus(){
        return  this.status == TransactionStatus.APPROVED
                || this.status == TransactionStatus.CANCELLED
                || this.status == TransactionStatus.FAILED;
    }

    //intetions methods
    public void markAsApproved(){

        //does not make sense cancelled to approve
        if(isFinalStatus()){
            throw new BusinessException("Cannot change status from final stage:" + this.status);
        }
        this.status = TransactionStatus.APPROVED;
    }

    public void markAsFailed(){
        if(isFinalStatus()){
            throw  new BusinessException("Cannot change status from final stage" + this.status);
        }
        this.status = TransactionStatus.FAILED;
    }

    public void markAsCancelled(){
        if(isFinalStatus()){
            throw new BusinessException("Cannot change status from final stage" + this.status);
        }

        this.status = TransactionStatus.CANCELLED;
    }





    public String getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount; //
    }


    public TransactionStatus getStatus() {
        return status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


}
