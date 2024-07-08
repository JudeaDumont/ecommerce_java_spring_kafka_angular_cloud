package com.alibou.ecommerce.customer;

import com.alibou.ecommerce.customer.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        Customer customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.id()).orElseThrow(() -> new CustomerNotFoundException(
                "Cannot find customer with id " + request.id()
        ));
        mergerCustomer(customer, request);
        repository.save(customer);

    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstName())) {
            customer.setFirstName(request.firstName());
        }
        if (StringUtils.isNotBlank(request.lastName())) {
            customer.setLastName(request.lastName());
        }
        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }
        if (StringUtils.isNotBlank(request.address().getHouseNumber())) {
            customer.getAddress().setHouseNumber(request.address().getHouseNumber());
        }
        if (StringUtils.isNotBlank(request.address().getStreet())) {
            customer.getAddress().setStreet(request.address().getStreet());
        }
        if (StringUtils.isNotBlank(request.address().getZipCode())) {
            customer.getAddress().setZipCode(request.address().getZipCode());
        }

    }

    public List<CustomerResponse> findAllCustomers() {

        return repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
        return repository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId).map(mapper::fromCustomer)
                .orElseThrow(()-> new CustomerNotFoundException(
                        "Cannot find customer with id " + customerId
                ));
    }

    public void deleteCustomer(String customerId) {
        repository.deleteById(customerId);
    }
}