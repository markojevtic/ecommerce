package com.prodyna.ecommerce.server.repository;

import com.prodyna.ecommerce.server.repository.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
