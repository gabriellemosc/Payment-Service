package com.gabriel.payment.gateway;

import com.gabriel.payment.domain.Transaction;
import com.gabriel.payment.gateway.dto.PaymentGatewayResponse;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

//will be used while we are at development
@Component
@Profile("dev")
public class FakePaymentGateway implements  PaymentGateway {

    @Override
    @Retry(name = "gatewayRetry", fallbackMethod = "fallbackPayment") //aply retry config
    public PaymentGatewayResponse createPayment(Transaction transaction){

        System.out.println("Try to send tou gateway...");

        if(ThreadLocalRandom.current().nextBoolean()){
            throw new RuntimeException("Failed Simuled gatewat");
        }
        //simulates a Extern ID by real gateway
        String fakeExternalId = UUID.randomUUID().toString();


        String fakeStatus = "PENDING";

        return new PaymentGatewayResponse(
                fakeExternalId,
                fakeStatus
        );

    }

    public PaymentGatewayResponse fallbackPayment(Transaction transaction, Exception ex){

        //return a response insted a error 500
        return new PaymentGatewayResponse(
                null,
                "FAILED"
        );
    }
}
