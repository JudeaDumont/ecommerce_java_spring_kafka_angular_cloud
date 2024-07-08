package com.alibou.ecommerce.orderLine;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    Collection<OrderLine> findAllByOrderId(Integer orderId);
}
