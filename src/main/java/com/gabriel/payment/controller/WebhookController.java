package com.gabriel.payment.controller;


import com.gabriel.payment.dto.UpdateStatusRequest;
import com.gabriel.payment.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private final TransactionService transactionService;

    public WebhookController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Void> updateTransactionStatus(@RequestBody UpdateStatusRequest request){

        transactionService.updateStatus(
                request.getTransactionId(),
                request.getStatus()                 //get new status
        );

        return ResponseEntity.ok().build(); //return 200
    }

}
