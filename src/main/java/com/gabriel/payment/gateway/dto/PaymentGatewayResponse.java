package com.gabriel.payment.gateway.dto;

//  standard response any gateway must give
public record PaymentGatewayResponse(
        //transporting immutable data after creation
        String externalId, // ID the gateway makes to identify the payment
        String status      // status returned by gateway
) {}
