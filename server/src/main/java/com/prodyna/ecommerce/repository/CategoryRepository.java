package com.prodyna.ecommerce.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.prodyna.ecommerce.repository.entity.Category;

public interface CategoryRepository extends MongoRepository <Category, String> {
	

}
