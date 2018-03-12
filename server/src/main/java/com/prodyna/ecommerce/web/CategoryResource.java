package com.prodyna.ecommerce.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import com.prodyna.ecommerce.repository.entity.Category;
import com.prodyna.ecommerce.resource.dto.CategoryDTO;
import com.prodyna.ecommerce.services.CategoryService;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(value = "/category", produces = APPLICATION_JSON_UTF8_VALUE)
public class CategoryResource {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ConversionService conversionService;

	public static final ControllerLinkBuilder createLink() {
		return linkTo(CategoryResource.class);
	}

	public static final ControllerLinkBuilder creatSingleLink(String id) {
		return linkTo(methodOn(CategoryResource.class).get(id));
	}

	@RequestMapping(method = GET)
	public ResponseEntity<List<CategoryDTO>> getAll() {
		List<Category> categories = categoryService.getAll();

		TypeDescriptor typeCategory = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Category.class));
		TypeDescriptor typeCategoryDTO = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(CategoryDTO.class));

		List<CategoryDTO> categoriesDTO = (List<CategoryDTO>) conversionService.convert(categories, typeCategory, typeCategoryDTO);

		return ResponseEntity.ok(categoriesDTO);
	}

	@RequestMapping(method = GET, path = "/{id}")
	public ResponseEntity<CategoryDTO> get(@PathVariable String categoryId) {
		Category category = categoryService.load(categoryId);
		CategoryDTO categoryDTO = conversionService.convert(category, CategoryDTO.class);

		return ResponseEntity.ok(categoryDTO);
	}

	@RequestMapping(method = POST)
	public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO categoryDTO) {
		Category category = conversionService.convert(categoryDTO, Category.class);
		CategoryDTO inserted = conversionService.convert(categoryService.insert(category), CategoryDTO.class);

		return ResponseEntity.ok(inserted);
	}

	@RequestMapping(method = PUT)
	public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO categoryDTO) {
		Category category = conversionService.convert(categoryDTO, Category.class);
		CategoryDTO updated = conversionService.convert(categoryService.update(category), CategoryDTO.class);

		return ResponseEntity.ok(updated);
	}

	@RequestMapping(method = DELETE, path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String categoryId) {
		categoryService.delete(categoryId);

		return ResponseEntity.noContent().build();
	}

}
