package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.Product;
import com.prodyna.ecommerce.server.resource.dto.ProductDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoToProductConverter implements Converter<ProductDto, Product>{
    @Override
    public Product convert(ProductDto product) {
        return Product.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .description(product.getDescription())
                .photoUrl(product.getPhotoUrl())
                .category(product.getCategory())
                .active(product.getActive())
                .build();
    }
}
