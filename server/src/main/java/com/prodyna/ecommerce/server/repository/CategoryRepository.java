package com.prodyna.ecommerce.server.repository;

import com.prodyna.ecommerce.server.repository.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
