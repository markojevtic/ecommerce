package com.prodyna.ecommerce.server.repository;

import com.prodyna.ecommerce.server.repository.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
