package com.prodyna.ecommerce.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.prodyna.ecommerce.exception.EntityNotFound;
import com.prodyna.ecommerce.repository.CategoryRepository;
import com.prodyna.ecommerce.repository.entity.Category;
import com.prodyna.ecommerce.services.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Optional<Category> load(String id) throws EntityNotFound{
		Optional<Category> category = categoryRepository.findById(id);
		if(!category.isPresent()) {
			throw new EntityNotFound();
		}
		return category;
	}

	@Override
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}

	@Override
	public void delete(String id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public Category insert(Category category) {
		return categoryRepository.insert(category);
	}

	@Override
	public Category update(Category category) {
		return categoryRepository.save(category);
	}

}
