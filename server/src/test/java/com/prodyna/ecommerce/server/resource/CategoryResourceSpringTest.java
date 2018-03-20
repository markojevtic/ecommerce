package com.prodyna.ecommerce.server.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prodyna.ecommerce.server.exception.EntityNotFoundException;
import com.prodyna.ecommerce.server.repository.entity.Category;
import com.prodyna.ecommerce.server.resource.dto.CategoryDto;
import com.prodyna.ecommerce.server.services.CategoryService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryResourceSpringTest {

    private final static String TEST_ID = "22";
    private final static String TEST_NAME = "Test name";
    private final static Category TEST_CATEGORY = Category.builder().categoryId(TEST_ID).name(TEST_NAME).build();

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ConversionService conversionService;

    @MockBean
    private CategoryService categoryService;

    private MockMvc mockMvc;

    @Before
    public void setupMvcMock() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void loadCategoryReturnsProperResourceAndStatusOk() throws Exception {
        doReturn(TEST_CATEGORY)
                .when(categoryService)
                .load(anyString());

        mockMvc.perform(get(CategoryResource.createSingleResourceLink(TEST_ID).toString()).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value(TEST_ID))
                .andExpect(jsonPath("$.name").value(TEST_CATEGORY.getName()));
    }

    @Test
    public void loadCategoryReturnsNotFoundStatusOnServiceEntityNotFoundException() throws Exception {
        doThrow(EntityNotFoundException.class)
                .when(categoryService)
                .load(anyString());

        mockMvc.perform(get(CategoryResource.createSingleResourceLink(TEST_ID).toString()).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllReturnsProperResourceAndStatusOk() throws Exception {
        doReturn(Collections.singletonList(TEST_CATEGORY))
                .when(categoryService)
                .getAll();

        mockMvc.perform(get(CategoryResource.createResourceLink().toString()).accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[0].categoryId").value(TEST_ID))
                .andExpect(jsonPath("$[0].name").value(TEST_CATEGORY.getName()));
    }

    @Test
    public void deletePerformsProperActionAndStatusIsNoContent() throws Exception {
        mockMvc.perform(delete(CategoryResource.createSingleResourceLink(TEST_ID).toString()).accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isNoContent());

        verify(categoryService, times(1)).delete(eq(TEST_ID));
    }

    @Test
    public void insertReturnsProperResourceAndStatusIsOk() throws Exception {
        doReturn(TEST_CATEGORY)
                .when(categoryService)
                .insert(any(Category.class));

        mockMvc.perform(post(CategoryResource.createResourceLink().toString()).accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(TEST_CATEGORY)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value(TEST_ID))
                .andExpect(jsonPath("$.name").value(TEST_CATEGORY.getName()));
    }

    @Test
    public void updateReturnsProperResourceAndStatusIsOk() throws Exception {
        doReturn(TEST_CATEGORY)
                .when(categoryService)
                .update(any(Category.class));

        mockMvc.perform(post(CategoryResource.createResourceLink().toString() + "/update").accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(TEST_CATEGORY)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value(TEST_ID))
                .andExpect(jsonPath("$.name").value(TEST_CATEGORY.getName()));
    }

    @Test
    public void conversionServiceIsAvailableAndConvertsEntityToDto() {
        assertThat(conversionService).isNotNull();
        assertThat(conversionService.canConvert(Category.class, CategoryDto.class)).isTrue();
    }

    @Test
    public void resourceLinkAndResourceSingleLinkAreCreated() {
        Link resourcesLink = CategoryResource.createResourceLink().withSelfRel();
        Link singleResourceLink = CategoryResource.createSingleResourceLink("13").withSelfRel();
        assertThat(resourcesLink.getHref()).endsWith("/categories");
        assertThat(singleResourceLink.getHref()).endsWith("/categories/13");
    }
}
