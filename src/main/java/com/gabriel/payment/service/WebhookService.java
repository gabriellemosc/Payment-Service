package com.gabriel.payment.service;

import com.gabriel.payment.domain.WebhookEvent;
import com.gabriel.payment.dto.WebhookRequest;
import com.gabriel.payment.repository.WebhookEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class WebhookService {

    private final WebhookEventRepository webhookEventRepository;
    private final TransactionService transactionService;

    public WebhookService(WebhookEventRepository webhookEventRepository,
                          TransactionService transactionService) {
        this.webhookEventRepository = webhookEventRepository;
        this.transactionService = transactionService;
    }

    @Transactional
    public void process(WebhookRequest request){

        //rowns insert on DB
        int rowsInserted = webhookEventRepository.insertIgnoreConflict(
                UUID.randomUUID(),
                request.getEventId(),
                request.getTransactionId(),
                LocalDateTime.now()
        );

        if(rowsInserted == 0){      //if none rows was insert, was duplicated and return
            return;
        }

        //after validation pass to the service transaction
        transactionService.updateStatus(
                request.getTransactionId(),
                request.getStatus()
        );


    }
}
