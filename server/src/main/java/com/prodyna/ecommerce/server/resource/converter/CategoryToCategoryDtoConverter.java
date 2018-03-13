package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.Category;
import com.prodyna.ecommerce.server.resource.dto.CategoryDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryDtoConverter implements Converter<Category, CategoryDto> {
    @Override
    public CategoryDto convert(Category category) {
        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .build();
    }
}
