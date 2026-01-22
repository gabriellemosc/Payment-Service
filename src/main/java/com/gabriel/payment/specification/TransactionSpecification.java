package com.gabriel.payment.specification;

import com.gabriel.payment.domain.Transaction;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionSpecification {

    public static Specification<Transaction> hasStatus(String status){
        return(root, query, criteriaBuilder) ->
                status == null
                ? null      //if NULL the filter is not apllied
                : criteriaBuilder.equal(root.get("status"), status);        //acess camp 'status'
    }

    public static Specification<Transaction> createdAfter(LocalDateTime date){
        return (root, query, criteriaBuilder) ->
                date == null
                ? null
                : criteriaBuilder.greaterThanOrEqualTo(
                        root.get("createdAt"),
                        date
                );
    }

    public static Specification<Transaction> createdBefore(LocalDateTime date){
        return (root, query, criteriaBuilder) ->
                date == null
                ? null
                : criteriaBuilder.lessThanOrEqualTo(
                        root.get("createdAt"),
                        date
                );

    }

    public static Specification<Transaction> amountGreaterThan(BigDecimal value){
        return (root, query, criteriaBuilder) ->
                value == null
                ? null
                : criteriaBuilder.greaterThanOrEqualTo(
                        root.get("amount"),
                        value
                );
    }

    public static Specification<Transaction> amountLessThan(BigDecimal value){
        return (root, query, criteriaBuilder) ->
               value == null
               ? null
               : criteriaBuilder.lessThanOrEqualTo(
                       root.get("amount"),
                       value
               );
    }
}
