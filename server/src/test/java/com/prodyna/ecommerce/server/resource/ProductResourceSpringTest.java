package com.prodyna.ecommerce.server.resource;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.prodyna.ecommerce.server.exception.EntityNotFoundException;
import com.prodyna.ecommerce.server.repository.entity.Category;
import com.prodyna.ecommerce.server.repository.entity.Product;
import com.prodyna.ecommerce.server.resource.dto.ProductDto;
import com.prodyna.ecommerce.server.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductResourceSpringTest {

    private final static String TEST_ID = "22";
    private final static String TEST_NAME = "Product name";
    private final static String TEST_DESCRIPTION = "Product desc";
    private final static String TEST_PHOTO_URL = "/test/url/bla.jpg";
    private final static Category TEST_CATEGORY = Category.builder().categoryId(TEST_ID).name(TEST_NAME).build();
    private final static Product TEST_PRODUCT = Product.builder().productId(TEST_ID).name(TEST_NAME).description(TEST_DESCRIPTION)
            .photoUrl(TEST_PHOTO_URL).category(TEST_CATEGORY).active(true).build();

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ConversionService conversionService;

    @MockBean
    private ProductService productService;

    private MockMvc mockMvc;

    @Before
    public void setupMvcMock() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void loadProductReturnsProperResourceAndStatusOk() throws Exception {
        doReturn(TEST_PRODUCT)
                .when(productService)
                .load(anyString());

        mockMvc.perform(get(ProductResource.createResourceSingleLink(TEST_ID).toString()).accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(TEST_ID))
                .andExpect(jsonPath("$.name").value(TEST_PRODUCT.getName()))
                .andExpect(jsonPath("$.description").value(TEST_PRODUCT.getDescription()))
                .andExpect(jsonPath("$.photoUrl").value(TEST_PRODUCT.getPhotoUrl()))
                .andExpect(jsonPath("$.category").value(TEST_PRODUCT.getCategory()))
                .andExpect(jsonPath("$.active").value(TEST_PRODUCT.getActive()));
    }

    @Test
    public void loadProductReturnsNotFoundStatusOnServiceEntityNotFoundException() throws Exception {
        doThrow(EntityNotFoundException.class)
                .when(productService)
                .load(anyString());

        mockMvc.perform(get(ProductResource.createResourceSingleLink(TEST_ID).toString()).accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isNotFound());
    }

    @Test
    public void getAllReturnsProperResourceAndStatusOk() throws Exception {
        doReturn(Collections.singletonList(TEST_PRODUCT))
                .when(productService)
                .getAll();

        mockMvc.perform(get(ProductResource.createResourceLink().toString()).accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[0].productId").value(TEST_ID))
                .andExpect(jsonPath("$[0].name").value(TEST_PRODUCT.getName()))
                .andExpect(jsonPath("$[0].description").value(TEST_PRODUCT.getDescription()))
                .andExpect(jsonPath("$[0].photoUrl").value(TEST_PRODUCT.getPhotoUrl()))
                .andExpect(jsonPath("$[0].active").value(TEST_PRODUCT.getActive()))
                .andExpect(jsonPath("$[0].category.name").value(TEST_PRODUCT.getCategory().getName()))
                .andExpect(jsonPath("$[0].category.categoryId").value(TEST_PRODUCT.getCategory().getCategoryId()));
    }

    @Test
    public void deletePerformsProperActionAndStatusIsNoContent() throws Exception {
        mockMvc.perform(delete(ProductResource.createResourceSingleLink(TEST_ID).toString()).accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isNoContent());

        verify(productService, times(1)).delete(eq(TEST_ID));
    }

    @Test
    public void insertReturnsProperResourceAndStatusIsOk() throws Exception {
        doReturn(TEST_PRODUCT)
                .when(productService)
                .insert(any(Product.class));

        mockMvc.perform(post(ProductResource.createResourceLink().toString()).accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(TEST_PRODUCT)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(TEST_ID))
                .andExpect(jsonPath("$.name").value(TEST_PRODUCT.getName()))
                .andExpect(jsonPath("$.description").value(TEST_PRODUCT.getDescription()))
                .andExpect(jsonPath("$.photoUrl").value(TEST_PRODUCT.getPhotoUrl()))
                .andExpect(jsonPath("$.category").value(TEST_PRODUCT.getCategory()))
                .andExpect(jsonPath("$.active").value(TEST_PRODUCT.getActive()));
    }

    @Test
    public void updateReturnsProperResourceAndStatusIsOk() throws Exception {
        doReturn(TEST_PRODUCT)
                .when(productService)
                .update(any(Product.class));

        mockMvc.perform(post(ProductResource.createResourceLink().toString() + "/update").accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(TEST_PRODUCT)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(TEST_ID))
                .andExpect(jsonPath("$.name").value(TEST_PRODUCT.getName()))
                .andExpect(jsonPath("$.description").value(TEST_PRODUCT.getDescription()))
                .andExpect(jsonPath("$.photoUrl").value(TEST_PRODUCT.getPhotoUrl()))
                .andExpect(jsonPath("$.category").value(TEST_PRODUCT.getCategory()))
                .andExpect(jsonPath("$.active").value(TEST_PRODUCT.getActive()));
    }

    @Test
    public void conversionServiceIsAvailableAndConvertsEntityToDto() {
        assertThat(conversionService).isNotNull();
        assertThat(conversionService.canConvert(Product.class, ProductDto.class)).isTrue();
    }

    @Test
    public void resourceLinkAndResourceSingleLinkAreCreated() {
        Link resourcesLink = ProductResource.createResourceLink().withSelfRel();
        Link singleResourceLink = ProductResource.createResourceSingleLink("13").withSelfRel();
        assertThat(resourcesLink.getHref()).endsWith("/products");
        assertThat(singleResourceLink.getHref()).endsWith("/products/13");
    }
}
