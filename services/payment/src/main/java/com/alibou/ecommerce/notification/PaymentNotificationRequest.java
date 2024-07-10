package com.alibou.ecommerce.notification;

import com.alibou.ecommerce.payment.PaymentMethod;

import java.math.BigDecimal;

//Must match PaymentConfirmation
public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {

}
