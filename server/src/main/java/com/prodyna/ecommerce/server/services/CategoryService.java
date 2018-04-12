package com.prodyna.ecommerce.server.services;

import com.prodyna.ecommerce.server.repository.entity.Category;

import java.util.List;

public interface CategoryService {
    Category load(String id);

    Category insert(Category customer);

    Category update(Category customer);

    void delete(String id);

    List<Category> getAll();
}
