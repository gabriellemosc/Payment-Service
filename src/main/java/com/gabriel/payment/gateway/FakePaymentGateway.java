package com.gabriel.payment.gateway;

import com.gabriel.payment.domain.Transaction;
import com.gabriel.payment.gateway.dto.PaymentGatewayResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.UUID;

//will be used while we are at development
@Component
@Profile("dev")
public class FakePaymentGateway implements  PaymentGateway {

    @Override
    public PaymentGatewayResponse createPayment(Transaction transaction){

        //simulates a Extern ID by real gateway
        String fakeExternalId = UUID.randomUUID().toString();


        String fakeStatus = "PENDING";

        return new PaymentGatewayResponse(
                fakeExternalId,
                fakeStatus
        );

    }
}
