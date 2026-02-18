package com.gabriel.payment.gateway;

import com.gabriel.payment.domain.Transaction;
import com.gabriel.payment.gateway.dto.PaymentGatewayResponse;

public interface PaymentGateway {

        PaymentGatewayResponse createPayment(Transaction transaction);
}
