package com.alibou.ecommerce.kafka.payment;

import java.math.BigDecimal;
//must match PaymentNotificationRequest
public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
