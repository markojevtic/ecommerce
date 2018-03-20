package com.prodyna.ecommerce.server.repository;

import com.prodyna.ecommerce.server.repository.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
