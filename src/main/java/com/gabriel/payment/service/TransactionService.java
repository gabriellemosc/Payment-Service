package com.gabriel.payment.service;

import com.gabriel.payment.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.gabriel.payment.domain.Transaction;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;  //acess the DB. Interface JPA

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository; //dependencias inject by constructor
    }


    @Transactional //ATOMIC
    public Transaction createTransaction(BigDecimal amount, String transactionId){  // the values come from the Controller

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
}
