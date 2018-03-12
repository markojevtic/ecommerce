package com.prodyna.ecommerce.server.services;

import com.prodyna.ecommerce.server.repository.entity.Product;
import com.prodyna.ecommerce.server.resource.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    ProductDto load(final String productId);
    ProductDto insert(final Product product);
    ProductDto update(final Product product);
    void delete(final String productId);
    List<ProductDto> getAll();
}
