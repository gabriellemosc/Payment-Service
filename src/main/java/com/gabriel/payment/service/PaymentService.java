package com.gabriel.payment.service;
import com.gabriel.payment.domain.Transaction;
import com.gabriel.payment.gateway.PaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentGateway paymentGateway;

    PaymentService(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;

    }

    public void pay(Transaction transaction){
        paymentGateway.createPayment(transaction);
    }

}
