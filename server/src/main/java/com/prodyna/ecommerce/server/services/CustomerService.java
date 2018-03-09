package com.prodyna.ecommerce.server.services;

import com.prodyna.ecommerce.server.repository.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer get(Long id);

    List<Customer> getAll();

    void delete(Long id);

    Customer save(Customer customer);
}
