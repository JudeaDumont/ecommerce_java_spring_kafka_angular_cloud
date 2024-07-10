package com.alibou.ecommerce.payment;

import com.alibou.ecommerce.order.CustomerResponse;
import com.alibou.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest (
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer

) {
}
