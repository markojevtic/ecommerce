package com.prodyna.ecommerce.resource.converter;

import org.springframework.core.convert.converter.Converter;

import com.prodyna.ecommerce.repository.entity.Category;
import com.prodyna.ecommerce.resource.dto.CategoryDTO;


public class CategoryToCategoryDTOConverter implements Converter<Category, CategoryDTO> {

	@Override
	public CategoryDTO convert(Category category) {
		return CategoryDTO.builder().categoryId(category.getCategoryId()).name(category.getName()).build();
	}

    
}
