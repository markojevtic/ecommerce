package com.prodyna.ecommerce.server.service;

import com.prodyna.ecommerce.server.exception.EntityNotFoundException;
import com.prodyna.ecommerce.server.repository.CategoryRepository;
import com.prodyna.ecommerce.server.repository.ComponentTest;
import com.prodyna.ecommerce.server.repository.entity.Category;
import com.prodyna.ecommerce.server.services.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.ServiceLoader;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceComponentTest extends ComponentTest {

    private final static String ID = "456";
    private final static String NAME = "Special category";

    private final static String ID_BELGRADE = "123456";
    private final static String NAME_BELGRADE = "Belgrade category";

    private final static Category TEST_CATEGORY = Category.builder().categoryId(ID).build();
    private static final Category TEST_CATEGORY_BELGRADE = Category.builder().categoryId(ID_BELGRADE).name(NAME_BELGRADE).build();

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void insertCategoryPersistsProperEntity() {
        categoryService.insert(TEST_CATEGORY);
        Category retrievedCategory = categoryRepository.findById(TEST_CATEGORY.getCategoryId()).orElse(null);

        assertThat(retrievedCategory).isNotNull();
        assertThat(retrievedCategory).isEqualTo(TEST_CATEGORY);
    }

    @Test(expected = DuplicateKeyException.class)
    public void insertDuplicateCategoryThrowsException() {
        categoryService.insert(TEST_CATEGORY);
        categoryService.insert(TEST_CATEGORY);
    }

    @Test
    public void updateCategoryAltersProperEntity() {
        categoryService.insert(TEST_CATEGORY);
        Category retrievedCategory = categoryRepository.findById(TEST_CATEGORY.getCategoryId()).orElse(null);
        assertThat(retrievedCategory.getName()).isNull();

        TEST_CATEGORY.setName(NAME);
        categoryService.update(TEST_CATEGORY);
        retrievedCategory = categoryRepository.findById(TEST_CATEGORY.getCategoryId()).orElse(null);
        assertThat(retrievedCategory.getName()).isEqualTo(NAME);
    }

    @Test
    public void getAllReturnsAllInsertedCategories() {
        categoryService.insert(TEST_CATEGORY);
        categoryService.insert(TEST_CATEGORY_BELGRADE);

        List<Category> categories = categoryService.getAll();
        assertThat(categories).isNotNull();
        assertThat(categories).isNotEmpty();
        assertThat(categories.size()).isEqualTo(2);
        assertThat(categories.contains(TEST_CATEGORY));
        assertThat(categories.contains(TEST_CATEGORY_BELGRADE));
    }

    @Test
    public void deleteRemovesProperEntity() {
        categoryService.insert(TEST_CATEGORY_BELGRADE);
        categoryService.delete(TEST_CATEGORY_BELGRADE.getCategoryId());

        Category category = categoryRepository.findById(TEST_CATEGORY_BELGRADE.getCategoryId()).orElse(null);
        assertThat(category).isNull();
    }

    @Test
    public void loadReturnsProperEntity() {
        categoryService.insert(TEST_CATEGORY_BELGRADE);
        Category loadedCategory = categoryService.load(TEST_CATEGORY_BELGRADE.getCategoryId());

        assertThat(loadedCategory).isEqualTo(TEST_CATEGORY_BELGRADE);
    }

    @Test(expected = EntityNotFoundException.class)
    public void loadNonexistentCategoryThrowsException() {
        categoryService.load(ID);
    }
}
