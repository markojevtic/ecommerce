package com.prodyna.ecommerce.server.services.impl;

import com.prodyna.ecommerce.server.exception.EntityNotFoundException;
import com.prodyna.ecommerce.server.repository.CustomerRepository;
import com.prodyna.ecommerce.server.repository.entity.Customer;
import com.prodyna.ecommerce.server.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer load(String id) {
        return customerRepository.findById(id).orElseThrow(() -> EntityNotFoundException.createExceptionByEntityAndId(Customer.class, id));
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public void delete(String id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer insert(Customer customer) {
        return customerRepository.insert(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }
}
