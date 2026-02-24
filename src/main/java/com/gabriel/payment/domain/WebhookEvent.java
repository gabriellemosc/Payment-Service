package com.gabriel.payment.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "webhook_event",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "eventId") //
        }
                )
public class WebhookEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String eventId; //unique ID send by gateway


    private String transactionId; //reference transaction affected

    private LocalDateTime processedAt;

    @PrePersist
    public void prePersist(){
        this.processedAt = LocalDateTime.now();
    }

    public String getEventId(){
        return this.eventId;
    }

    public String gettransactionId(){
        return this.transactionId;
    }

    public LocalDateTime getDateTime(){
        return this.processedAt;
    }

    public void setEventId(String eventId){
        this.eventId = eventId;
    }

    public void setTransactionId(String transactionId){
        this.transactionId = transactionId;
    }

    public void setProcessedAt(LocalDateTime processedAt){
        this.processedAt = processedAt;
    }

}
