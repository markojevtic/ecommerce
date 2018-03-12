package com.prodyna.ecommerce.resource.converter;

import org.springframework.core.convert.converter.Converter;

import com.prodyna.ecommerce.repository.entity.Category;
import com.prodyna.ecommerce.resource.dto.CategoryDTO;

public class CategoryDTOToCategoryConverter implements Converter<CategoryDTO, Category> {

	@Override
	public Category convert(CategoryDTO categoryDTO) {
		return Category.builder()
				.categoryId( categoryDTO.getCategoryId() )
				.name( categoryDTO.getName()) 
				.build();
	}

}
