package com.prodyna.ecommerce.services;

import java.util.List;
import java.util.Optional;

import com.prodyna.ecommerce.repository.entity.Category;

public interface CategoryService {

	Optional<Category> load(String id);

	List<Category> getAll();

	void delete(String id);

	Category insert(Category category);

	Category update(Category category);
}
