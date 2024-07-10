package com.alibou.ecommerce.payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(PaymentRequest request) {

        return Payment.builder()
                .paymentMethod(request.paymentMethod())
                .amount(request.amount())
                .id(request.id())
                .orderId(request.orderId())
                .build();
    }
}