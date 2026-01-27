package com.gabriel.payment.domain;
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

    //intetions methods
    public void markAsApproved(){

        //does not make sense cancelled to approve
        if(this.status != TransactionStatus.CREATED &&
        this.status != TransactionStatus.PENDING){
            throw new IllegalStateException(("Cannot approve transaction in status:" + this.status));
        }
        this.status = TransactionStatus.APPROVED;
    }

    public void markAsFailed(){
        if(this.status == TransactionStatus.APPROVED){
            throw  new IllegalStateException("Cannot fail an already approved transaction");
        }
        this.status = TransactionStatus.FAILED;
    }

    public void markAsCancelled(){
        if(this.status == TransactionStatus.APPROVED){
            throw new IllegalStateException(("Cannot cancel a approved transaction"));
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
