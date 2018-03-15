package com.prodyna.ecommerce.server.resource;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.prodyna.ecommerce.server.exception.EntityNotFoundException;
import com.prodyna.ecommerce.server.repository.entity.Customer;
import com.prodyna.ecommerce.server.resource.dto.CustomerDto;
import com.prodyna.ecommerce.server.services.CustomerService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerResourceSpringTest {

    private final static String TEST_ID = "22";
    private final static Customer TEST_CUSTOMER = Customer.builder().customerId(TEST_ID).active(true).name("Test customer").build();
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ConversionService conversionService;

    @MockBean
    private CustomerService customerService;

    private MockMvc mockMvc;

    @Before
    public void setupMvcMock() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void loadCustomerReturnsProperResourceAndStatusOk() throws Exception {
        doReturn(TEST_CUSTOMER)
                .when(customerService)
                .load(anyString());

        mockMvc.perform(get(CustomerResource.createSingleResourceLink(TEST_ID).toString()).accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(TEST_ID))
                .andExpect(jsonPath("$.active").value(TEST_CUSTOMER.getActive()))
                .andExpect(jsonPath("$.name").value(TEST_CUSTOMER.getName()));
    }

    @Test
    public void loadCustomerReturnsNotFoundStatusOnServiceEntityNotFoundException() throws Exception {
        doThrow(EntityNotFoundException.class)
                .when(customerService)
                .load(anyString());

        mockMvc.perform(get(CustomerResource.createSingleResourceLink(TEST_ID).toString()).accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isNotFound());
    }

    @Test
    public void getAllReturnsProperResourceAndStatusOk() throws Exception {
        doReturn(Collections.singletonList(TEST_CUSTOMER))
                .when(customerService)
                .getAll();

        mockMvc.perform(get(CustomerResource.createLink().toString()).accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[0].customerId").value(TEST_ID))
                .andExpect(jsonPath("$[0].active").value(TEST_CUSTOMER.getActive()))
                .andExpect(jsonPath("$[0].name").value(TEST_CUSTOMER.getName()));
    }

    @Test
    public void deletePerformsProperActionAndStatusIsNoContent() throws Exception {
        mockMvc.perform(delete(CustomerResource.createSingleResourceLink(TEST_ID).toString()).accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isNoContent());

        verify(customerService, times(1)).delete(eq(TEST_ID));
    }

    @Test
    public void insertReturnsProperResourceAndStatusIsOk() throws Exception {
        doReturn(TEST_CUSTOMER)
                .when(customerService)
                .insert(any(Customer.class));

        mockMvc.perform(post(CustomerResource.createLink().toString()).accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(TEST_CUSTOMER)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(TEST_ID))
                .andExpect(jsonPath("$.active").value(TEST_CUSTOMER.getActive()))
                .andExpect(jsonPath("$.name").value(TEST_CUSTOMER.getName()));
    }

    @Test
    public void updateReturnsProperResourceAndStatusIsOk() throws Exception {
        doReturn(TEST_CUSTOMER)
                .when(customerService)
                .update(any(Customer.class));

        mockMvc.perform(post(CustomerResource.createLink().toString() + "/update").accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(TEST_CUSTOMER)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(TEST_ID))
                .andExpect(jsonPath("$.active").value(TEST_CUSTOMER.getActive()))
                .andExpect(jsonPath("$.name").value(TEST_CUSTOMER.getName()));
    }

    @Test
    public void conversionServiceIsAvailableAndConvertsEntityToDto() {
        assertThat(conversionService).isNotNull();
        assertThat(conversionService.canConvert(Customer.class, CustomerDto.class)).isTrue();
    }

    @Test
    public void resourceLinkAndResourceSingleLinkAreCreated() {
        Link resourcesLink = CustomerResource.createLink().withSelfRel();
        Link singleResourceLink = CustomerResource.createSingleResourceLink("13").withSelfRel();
        assertThat(resourcesLink.getHref()).endsWith("/customers");
        assertThat(singleResourceLink.getHref()).endsWith("/customers/13");
    }
}