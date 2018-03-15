package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.Product;
import com.prodyna.ecommerce.server.resource.dto.CategoryDto;
import com.prodyna.ecommerce.server.resource.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductDtoConverter extends ContextAwareConverter implements Converter<Product, ProductDto>{

    @Override
    public ProductDto convert(Product product) {
        return ProductDto.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .description(product.getDescription())
                .photoUrl(product.getPhotoUrl())
                .category(conversionService().convert(product.getCategory(), CategoryDto.class))
                .active(product.getActive())
                .build();
    }
}
