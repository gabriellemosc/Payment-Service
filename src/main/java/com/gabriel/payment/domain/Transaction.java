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
    private TransactionStatus status;  //CREATED, SUCESS, FAILED

    @Column(nullable = false, unique = true)
    private String transactionId;

    @Column(nullable = false)   //obrigation date
    private LocalDateTime createdAt;

    public Transaction() {}

    public Transaction(BigDecimal amount, String transactionId){
        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        this.amount = amount;
        this.transactionId =  transactionId;
        this.status = TransactionStatus.CREATED;
        this.createdAt = LocalDateTime.now();

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
