package com.gabriel.payment.controller;

import com.gabriel.payment.domain.Transaction;
import com.gabriel.payment.domain.TransactionStatus;
import com.gabriel.payment.dto.CreateTransactionRequest;
import com.gabriel.payment.dto.TransactionResponse;
import com.gabriel.payment.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     //responde http and return JSON
@RequestMapping("/transactions")    //base route of API
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping // POST / transactions
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody CreateTransactionRequest request) {   //take at URL body and transform in JAVA OBJECT
        Transaction transaction = transactionService.createTransaction(
                request.getAmount(),    //comes from JSON
                request.getTransactionId()
        );

        //Response entity?
        return ResponseEntity.status(HttpStatus.CREATED).body(new TransactionResponse(transaction));  //HTTP 201 and return DTO of respond after pass to the logic
    }


    @GetMapping("/{id}") //GET /transactions/{id}
    public ResponseEntity<TransactionResponse> findbyId(@PathVariable String id){
        Transaction transaction = transactionService.findTransactionbyId(id);

        return ResponseEntity.ok(new TransactionResponse(transaction)); //HTTP 200
    }

    @PatchMapping("/{id}/status") //PATCH /transactions/{id}/status
    public ResponseEntity<TransactionResponse> updateStatus(@PathVariable String id, @RequestParam TransactionStatus status){

    Transaction transaction = transactionService.updateStatus(id, status);

    return ResponseEntity.ok(new TransactionResponse(transaction));
    }
}
