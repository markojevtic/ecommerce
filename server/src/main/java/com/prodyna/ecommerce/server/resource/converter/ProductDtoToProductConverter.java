package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.Product;
import com.prodyna.ecommerce.server.resource.dto.ProductDto;
import org.springframework.core.convert.converter.Converter;

public class ProductDtoToProductConverter implements Converter<ProductDto, Product> {

    @Override
    public Product convert(ProductDto productDto) {
        return Product.builder()
                .description(productDto.getDescription())
                .photoUrl(productDto.getPhotoUrl())
                .category(productDto.getCategory())
                .active(productDto.isActive())
                .build();
    }
}
