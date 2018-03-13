package com.prodyna.ecommerce.server.services;

import com.prodyna.ecommerce.server.repository.entity.Product;

import java.util.List;

public interface ProductService {

    Product load(String id);

    Product insert(Product product);

    Product update(Product product);

    void delete(String id);

    List<Product> getAll();
}
