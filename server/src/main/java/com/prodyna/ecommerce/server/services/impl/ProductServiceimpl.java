package com.prodyna.ecommerce.server.services.impl;

import com.prodyna.ecommerce.server.exception.EntityNotFoundException;
import com.prodyna.ecommerce.server.repository.ProductRepository;
import com.prodyna.ecommerce.server.repository.entity.Product;
import com.prodyna.ecommerce.server.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceimpl implements ProductService {

    private final ProductRepository productRepository;

    private EntityNotFoundException getProductNotFoundException(final Object id) {
        return EntityNotFoundException.createExceptionByEntityAndId(Product.class, id);
    }

    @Autowired
    public ProductServiceimpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product load(String id) {
        final Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            return product.get();
        } else throw getProductNotFoundException(id);
    }

    @Override
    public Product insert(Product product) {
        return null;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }
}
