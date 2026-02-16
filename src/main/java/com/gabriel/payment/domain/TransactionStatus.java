package com.gabriel.payment.domain;

import java.util.Map;
import java.util.Set;

public enum TransactionStatus {

    CREATED,
    PENDING,
    APPROVED,
    FAILED,
    CANCELLED;

    //transitions allowed
    private static final Map<TransactionStatus, Set<TransactionStatus>> allowedTransitions =
            Map.of(
                    CREATED, Set.of(PENDING, CANCELLED),   //CREATED can fo to PENDING or CANCELLED
                    PENDING, Set.of(APPROVED, FAILED),
                    APPROVED, Set.of(),
                    FAILED, Set.of(),
                    CANCELLED, Set.of()
            );

    public boolean canTransitionTo(TransactionStatus newStatus){

        return  allowedTransitions
                .getOrDefault(this, Set.of())
                .contains(newStatus);   //true if the transitions is allowed
    }

    public boolean isFinal(){
        return allowedTransitions.getOrDefault(this, Set.of()).isEmpty();
    }

}
