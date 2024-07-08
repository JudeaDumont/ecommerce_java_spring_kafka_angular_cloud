package com.alibou.ecommerce.order;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {

}
