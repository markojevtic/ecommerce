package com.prodyna.ecommerce.services;

import java.util.List;

import com.prodyna.ecommerce.repository.entity.Category;

public interface CategoryService {

	Category load(String id);

	List<Category> getAll();

	void delete(String id);

	Category insert(Category category);

	Category update(Category category);
}
