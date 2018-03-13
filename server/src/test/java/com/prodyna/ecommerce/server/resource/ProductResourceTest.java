package com.prodyna.ecommerce.server.resource;

import com.prodyna.ecommerce.server.services.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductResourceTest {

    @MockBean
    ProductService productService;



    @Test
    public void productNotFoundException() {

    }

    @Test
    public void loadProduct() {

    }

    @Test
    public void insertProduct() {

    }

    @Test
    public void updateProduct() {

    }

    @Test
    public void deleteProduct() {

    }

    @Test
    public void getAllProducts() {

    }
}
