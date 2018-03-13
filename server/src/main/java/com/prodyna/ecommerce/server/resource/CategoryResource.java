package com.prodyna.ecommerce.server.resource;

import com.prodyna.ecommerce.server.repository.entity.Category;
import com.prodyna.ecommerce.server.resource.dto.CategoryDto;
import com.prodyna.ecommerce.server.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/categories", produces = APPLICATION_JSON_UTF8_VALUE)
public class CategoryResource {

    @Autowired(required = false)
    private CategoryService categoryService;

    @Autowired
    private ConversionService conversionService;

    public static final ControllerLinkBuilder createLink() {
        return linkTo(CategoryResource.class);
    }

    public static final ControllerLinkBuilder createSingleLink(String id) {
        return linkTo(methodOn(CategoryResource.class).load(id));
    }

    @RequestMapping(method = GET, path = "/{id}")
    public ResponseEntity<CategoryDto> load(@PathVariable String id) {
        Category category = categoryService.load(id);
        CategoryDto categoryDto = conversionService.convert(category, CategoryDto.class);

        return ResponseEntity.ok(categoryDto);
    }

    @RequestMapping(method = GET)
    public ResponseEntity<List<CategoryDto>> getAll() {
        List<Category> categories = categoryService.getAll();

        TypeDescriptor typeDscCategories = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Category.class));
        TypeDescriptor typeDscCategoriesDto = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(CategoryDto.class));

        List<CategoryDto> categoriesDtos = (List<CategoryDto>) conversionService.convert(categories, typeDscCategories, typeDscCategoriesDto);

        return ResponseEntity.ok(categoriesDtos);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<CategoryDto> insert(@RequestBody CategoryDto categoryDto) {
        Category category = conversionService.convert(categoryDto, Category.class);
        Category insertedCategory = categoryService.insert(category);

        return ResponseEntity.ok(conversionService.convert(insertedCategory, CategoryDto.class));
    }

    @RequestMapping(method = POST, path = "/update")
    public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto categoryDto) {
        Category category = conversionService.convert(categoryDto, Category.class);
        Category updatedCategory = categoryService.update(category);

        return ResponseEntity.ok(conversionService.convert(updatedCategory, CategoryDto.class));
    }

    @RequestMapping(method = DELETE, path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        categoryService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
