package com.gabriel.payment.service;

import com.gabriel.payment.domain.TransactionStatus;
import com.gabriel.payment.exception.BusinessException;
import com.gabriel.payment.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.gabriel.payment.domain.Transaction;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.web.server.ResponseStatusException;
import com.gabriel.payment.exception.ResourceNotFoundException;
import com.gabriel.payment.gateway.dto.PaymentGatewayResponse;
import com.gabriel.payment.gateway.PaymentGateway;


@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;  //acess the DB. Interface JPA
    private final PaymentGateway paymentGateway;

    public TransactionService(TransactionRepository transactionRepository, PaymentGateway paymentGateway){
        this.transactionRepository = transactionRepository;
        this.paymentGateway = paymentGateway;
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

        //intern status
        transaction.changeStatus(TransactionStatus.PENDING);

        PaymentGatewayResponse gatewayResponse = paymentGateway.createPayment(transaction);

        transaction.setExternalId(gatewayResponse.externalId());    //save extern id generate by provider

        transaction.changeStatus(TransactionStatus.valueOf(gatewayResponse.status()));


        return  transactionRepository.save(transaction);    //save the entity on bank
    }


    public Transaction findTransactionbyId(String id){

        return transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                                                                            "Transaction Not Found"
        ));
    }


    @Transactional
    public Transaction updateStatus(String id, TransactionStatus newStatus){


        Transaction transaction = transactionRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Transaction Not Found"));
                ;

        transaction.changeStatus(newStatus);

        return transaction;

    }

}
