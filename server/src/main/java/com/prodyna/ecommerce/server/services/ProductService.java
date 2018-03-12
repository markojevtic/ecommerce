package com.prodyna.ecommerce.server.services;

import com.prodyna.ecommerce.server.repository.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    Product load(final String productId);
    Product insert(final Product product);
    Product update(final Product product);
    void delete(final String productId);
    List<Product> getAll();
}
