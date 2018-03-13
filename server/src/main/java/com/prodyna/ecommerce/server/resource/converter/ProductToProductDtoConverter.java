package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.Product;
import com.prodyna.ecommerce.server.resource.dto.ProductDto;
import org.springframework.core.convert.converter.Converter;

public class ProductToProductDtoConverter implements Converter<Product, ProductDto>  {

    @Override
    public ProductDto convert(Product product) {
        return ProductDto.builder()
                .description(product.getDescription())
                .photoUrl(product.getPhotoUrl())
                .category(product.getCategory())
                .active(product.isActive())
                .build();
    }
}
