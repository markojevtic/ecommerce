package com.prodyna.ecommerce.server.resource;

import com.prodyna.ecommerce.server.exception.ProductNotFoundException;
import com.prodyna.ecommerce.server.repository.entity.Product;
import com.prodyna.ecommerce.server.resource.dto.ProductDto;
import com.prodyna.ecommerce.server.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.springframework.http.ResponseEntity.ok;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductResourceTest {

    @MockBean
    ProductService productService;

    @Autowired
    ProductResource productResource;

    @Mock
    private ProductDto productDto;

    @Mock
    private Product product;

    @Mock
    private Product product2;

    private static final String PRODUCT_ID = "1";

    private List<Product> products = new ArrayList<>();

    private ResponseEntity responseEntitySingleProductDto;
    private ResponseEntity responseEntityMultipleProductDto;

    @Before
    public void setup() {

        final ResponseEntity.BodyBuilder builder = ok();
        responseEntitySingleProductDto = builder.body(productDto);
        responseEntityMultipleProductDto = builder.body(products);
        
        products.add(product);
        products.add(product2);
    }

    @Test
    public void loadProduct() {

        doReturn(this.productDto)
                .when(this.productService)
                .load(PRODUCT_ID);

        assertThat(this.productResource
                .load(PRODUCT_ID))
                .isEqualTo(responseEntitySingleProductDto);

        verify(this.productService, times(1))
                .load(PRODUCT_ID);
    }

    @Test
    public void insertProduct() {

        doReturn(this.productDto)
                .when(this.productService)
                .insert(this.product);

        assertThat(this.productResource
                .insert(this.product))
                .isEqualTo(responseEntitySingleProductDto);

        verify(this.productService, times(1))
                .insert(this.product);
    }

    @Test
    public void updateProduct() {

        doReturn(this.productDto)
                .when(this.productService)
                .update(this.product);

        assertThat(this.productResource
                .update(this.product))
                .isEqualTo(responseEntitySingleProductDto);

        verify(this.productService, times(1))
                .update(this.product);
    }

    @Test
    public void deleteProduct() {

        doReturn(this.productDto)
                .when(this.productService)
                .delete(PRODUCT_ID);

        assertThat(this.productResource
                .delete(PRODUCT_ID))
                .isEqualTo(responseEntitySingleProductDto);

        verify(this.productService, times(1))
                .delete(PRODUCT_ID);
    }

    @Test
    public void getAllProducts() {

        doReturn(this.products)
                .when(this.productService)
                .getAll();

        assertThat(this.productResource
                .getAll())
                .isEqualTo(this.responseEntityMultipleProductDto);
    }

    @Test
    public void productNotFoundExceptionOnLoad() {

        doThrow(new ProductNotFoundException())
                .when(this.productService)
                .load(PRODUCT_ID);

        assertThatThrownBy(() -> this.productResource
                .load(PRODUCT_ID))
                .isInstanceOf(ProductNotFoundException.class);

        verify(this.productService, times(1))
                .load(PRODUCT_ID);
    }

    @Test
    public void productNotFoundExceptionOnUpdate() {

        doThrow(new ProductNotFoundException())
                .when(this.productService)
                .update(this.product);

        assertThatThrownBy(() -> this.productResource
                .update(this.product))
                .isInstanceOf(ProductNotFoundException.class);

        verify(this.productService, times(1))
                .update(this.product);
    }

    @Test
    public void productNotFoundExceptionOnDelete() {

        doThrow(new ProductNotFoundException())
                .when(this.productService)
                .delete(PRODUCT_ID);

        assertThatThrownBy(() -> this.productResource
                .delete(PRODUCT_ID))
                .isInstanceOf(ProductNotFoundException.class);

        verify(this.productService, times(1))
                .delete(PRODUCT_ID);
    }

    @Test
    public void productNotFoundExceptionOnGetAll() {

        doThrow(new ProductNotFoundException())
                .when(this.productService)
                .getAll();

        assertThatThrownBy(() -> this.productResource
                .getAll())
                .isInstanceOf(ProductNotFoundException.class);

        verify(this.productService, times(1))
                .getAll();
    }
}
