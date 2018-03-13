package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.Category;
import com.prodyna.ecommerce.server.resource.dto.CategoryDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoToCategoryConverter implements Converter<CategoryDto, Category> {

    @Override
    public Category convert(CategoryDto categoryDto) {
        return Category.builder()
                .categoryId(categoryDto.getCategoryId())
                .name(categoryDto.getName())
                .build();
    }
}
