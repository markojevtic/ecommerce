package com.prodyna.ecommerce.server.services.impl;

import com.prodyna.ecommerce.server.exception.EntityNotFoundException;
import com.prodyna.ecommerce.server.repository.ProductRepository;
import com.prodyna.ecommerce.server.repository.entity.Product;
import com.prodyna.ecommerce.server.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product insert(Product product) {
        return productRepository.insert(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product load(String id) {
        return productRepository.findById(id).orElseThrow(() -> EntityNotFoundException.createExceptionByEntityAndId(Product.class, id));
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
