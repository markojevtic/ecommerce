package com.prodyna.ecommerce.server.service;

import com.prodyna.ecommerce.server.exception.EntityNotFoundException;
import com.prodyna.ecommerce.server.repository.ComponentTest;
import com.prodyna.ecommerce.server.repository.ProductRepository;
import com.prodyna.ecommerce.server.repository.entity.Category;
import com.prodyna.ecommerce.server.repository.entity.Product;
import com.prodyna.ecommerce.server.services.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceComponentTest extends ComponentTest {

    private final static String PRODUCT_ID = "456";
    private final static String NAME = "Eschborn Product";
    private final static String DESCRIPTION = "Product description";
    private final static String PHOTO_URL = "www.product.com/imageUrl";
    private final static Boolean ACTIVE = true;

    private final static String PRODUCT_BELGRADE_ID = "123456";
    private final static String NAME_BELGRADE = "Belgrade Product";

    private final static String CATEGORY_ID = "123456";
    private final static String CATEGORY_NAME = "Belgrade category";

    private static final Category TEST_CATEGORY = Category.builder().categoryId(CATEGORY_ID).name(CATEGORY_NAME).build();

    private final static Product TEST_PRODUCT = Product.builder().productId(PRODUCT_ID).name(NAME).description(DESCRIPTION)
            .category(TEST_CATEGORY).active(ACTIVE).build();
    private static final Product TEST_PRODUCT_BELGRADE = Product.builder().productId(PRODUCT_BELGRADE_ID).name(NAME_BELGRADE)
            .category(TEST_CATEGORY).active(ACTIVE).build();

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void insertProductPersistProperEntity() {
        productService.insert(TEST_PRODUCT);
        Product retrievedProduct = productRepository.findById(TEST_PRODUCT.getProductId()).orElse(null);

        assertThat(retrievedProduct).isNotNull();
        assertThat(retrievedProduct).isEqualTo(TEST_PRODUCT);
    }

    @Test(expected = DuplicateKeyException.class)
    public void insertDuplicateProductThrowsException() {
        productService.insert(TEST_PRODUCT);
        productService.insert(TEST_PRODUCT);
    }

    @Test
    public void updateProductAltersProperEntity() {
        productService.insert(TEST_PRODUCT);
        Product retrievedProduct = productRepository.findById(TEST_PRODUCT.getProductId()).orElse(null);
        assertThat(retrievedProduct.getPhotoUrl()).isNull();

        TEST_PRODUCT.setPhotoUrl(PHOTO_URL);
        productService.update(TEST_PRODUCT);
        retrievedProduct = productRepository.findById(TEST_PRODUCT.getProductId()).orElse(null);
        assertThat(retrievedProduct.getPhotoUrl()).isEqualTo(TEST_PRODUCT.getPhotoUrl());
    }

    @Test
    public void getAllReturnsAllInsertedProducts() {
        productService.insert(TEST_PRODUCT);
        productService.insert(TEST_PRODUCT_BELGRADE);

        List<Product> products = productService.getAll();
        assertThat(products).isNotNull();
        assertThat(products.size()).isEqualTo(2);
        assertThat(products.contains(TEST_PRODUCT));
        assertThat(products.contains(TEST_PRODUCT_BELGRADE));
    }

    @Test
    public void deleteRemovesProperProduct() {
        productService.insert(TEST_PRODUCT_BELGRADE);
        productService.delete(TEST_PRODUCT_BELGRADE.getProductId());

        Product product = productRepository.findById(TEST_PRODUCT_BELGRADE.getProductId()).orElse(null);
        assertThat(product).isNull();
    }

    @Test
    public void loadReturnsProperProduct() {
        productService.insert(TEST_PRODUCT);
        Product loadedProduct = productService.load(TEST_PRODUCT.getProductId());

        assertThat(loadedProduct).isEqualTo(TEST_PRODUCT);
    }

    @Test(expected = EntityNotFoundException.class)
    public void loadNonexistentProductThrowsException() {
        productService.load(PRODUCT_ID);
    }
}
