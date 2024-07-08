package com.alibou.ecommerce.orderLine;

import com.alibou.ecommerce.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .quantity(request.quantity())
                .order(Order.builder().id(request.orderId()).build())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return null;
    }
}
