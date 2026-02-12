package com.gabriel.payment.service;

import com.gabriel.payment.domain.TransactionStatus;
import com.gabriel.payment.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.gabriel.payment.domain.Transaction;
import java.math.BigDecimal;
import java.util.Optional;
import com.gabriel.payment.dto.CreateTransactionRequest;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;  //acess the DB. Interface JPA

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }


    @Transactional //ATOMIC
    public Transaction createTransaction(BigDecimal amount, String transactionId){  // the values come from the Controller

        if(transactionId == null || transactionId.trim().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                              "Amount must be bigger than 0"          );
        }

        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                    "Amount must be bigger than 0.");
        }

        Optional<Transaction> existingTransaction =     //optional something that cannot exists, without null
                    transactionRepository.findByTransactionId(transactionId);

        //if exist, return the transaction that exists
        if(existingTransaction.isPresent()) {
            return existingTransaction.get();       //avoid duplication

        }

        //create a new instance of Transaction
        Transaction transaction = new Transaction(amount, transactionId);

        return  transactionRepository.save(transaction);    //save the entity on bank
    }


    public Transaction findTransactionbyId(String id){

        return transactionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Transaction not found!"));
    }


    @Transactional
    public Transaction updateStatus(String id, TransactionStatus newStatus){


        Transaction transaction = transactionRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Transaction Not Found")
                )
                ;

        switch (newStatus){
            case APPROVED -> transaction.markAsApproved();
            case FAILED -> transaction.markAsFailed();
            case CANCELLED -> transaction.markAsCancelled();
            default -> throw new IllegalArgumentException("Status not supported");
        }

        return transaction;

    }

}
