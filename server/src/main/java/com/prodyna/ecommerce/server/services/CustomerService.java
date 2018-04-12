package com.prodyna.ecommerce.server.services;

import com.prodyna.ecommerce.server.repository.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer load(String id);

    List<Customer> getAll();

    void delete(String id);

    Customer insert(Customer customer);

    Customer update(Customer customer);
}
