package com.gabriel.payment.controller;


import com.gabriel.payment.domain.Transaction;
import com.gabriel.payment.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gabriel.payment.gateway.FakePaymentGateway;


@RestController
public class FakePaymentGatewayTest {

    private final PaymentService paymentService;

    public FakePaymentGatewayTest(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping("/test-payment")
    public String test(){
        Transaction transaction = new Transaction();
        paymentService.pay(transaction);

        return "Check Console logs";
    }


}
