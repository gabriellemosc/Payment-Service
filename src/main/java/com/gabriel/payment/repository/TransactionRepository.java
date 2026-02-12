package com.gabriel.payment.repository;
//TALK WITH ThE DB
import com.gabriel.payment.domain.Transaction;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransactionRepository extends JpaRepository<Transaction, String>, JpaSpecificationExecutor<Transaction> {

    Optional<Transaction> findByTransactionId(String transactionId);

}
