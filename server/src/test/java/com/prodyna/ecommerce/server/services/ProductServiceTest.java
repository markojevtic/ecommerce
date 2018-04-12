package com.prodyna.ecommerce.server.services;

import com.prodyna.ecommerce.server.exception.EntityNotFoundException;
import com.prodyna.ecommerce.server.repository.entity.Category;
import com.prodyna.ecommerce.server.repository.entity.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private MongoTemplate mongoTemplate;

    private final static String PRODUCT_ID1 = "1";
    private final static String PRODUCT_ID2 = "2";
    private final static String PRODUCT_NAME = "Product name";
    private final static String PRODUCT_DESCRIPTION = "Product desc";
    private final static String PRODUCT_PHOTO_URL = "test/product.jpg";
    private final static String CATEGORY_ID = "1";
    private final static String CATEGORY_NAME = "Category name";
    private final static Category PRODUCT_CATEGORY = Category.builder()
            .categoryId(CATEGORY_ID)
            .name(CATEGORY_NAME)
            .build();
    private final static Product TEST_PRODUCT1 = Product.builder()
            .productId(PRODUCT_ID1)
            .name(PRODUCT_NAME)
            .description(PRODUCT_DESCRIPTION)
            .photoUrl(PRODUCT_PHOTO_URL)
            .category(PRODUCT_CATEGORY)
            .active(true)
            .build();
    private final static Product TEST_PRODUCT2 = Product.builder()
            .productId(PRODUCT_ID2)
            .name(PRODUCT_NAME)
            .description(PRODUCT_DESCRIPTION)
            .photoUrl(PRODUCT_PHOTO_URL)
            .category(PRODUCT_CATEGORY)
            .active(true)
            .build();
    private final static String NEW_PRODUCT_NAME = "New Product name";
    private static final String PRODUCT_NOT_FOUND = "System could not find com.prodyna.ecommerce.server.repository.entity.Product with id %s.";

    @Before
    public void setUp() {
        this.productService.insert(TEST_PRODUCT1);
    }

    @After
    public void tearDown() {
        this.mongoTemplate.dropCollection(Product.class);
    }

    @Test
    public void loadProductTest() {
        assertEquals(TEST_PRODUCT1, this.productService.load(PRODUCT_ID1));
    }

    @Test
    public void updateProductTest() {
        assertEquals(TEST_PRODUCT1.getName(), this.productService.load(PRODUCT_ID1).getName());

        TEST_PRODUCT1.setName(NEW_PRODUCT_NAME);
        this.productService.update(TEST_PRODUCT1);
        final String updatedName = this.productService.load(PRODUCT_ID1).getName();

        assertEquals(NEW_PRODUCT_NAME, updatedName);
    }

    @Test
    public void deleteProductTest() {
        this.productService.delete(PRODUCT_ID1);
        assertThatThrownBy(() -> this.productService.load(PRODUCT_ID1))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage(String.format(PRODUCT_NOT_FOUND, PRODUCT_ID1));
    }

    @Test
    public void getAllProductsTest() {
        this.productService.insert(TEST_PRODUCT2);
        assertEquals(2, this.productService.getAll().size());
    }

    @Test
    public void insertDuplicateProduct() {
        assertThatThrownBy(() -> this.productService.insert(TEST_PRODUCT1))
                .isInstanceOf(DuplicateKeyException.class);
    }

}
