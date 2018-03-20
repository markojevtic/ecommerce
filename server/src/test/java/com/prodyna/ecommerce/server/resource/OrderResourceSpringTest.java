package com.prodyna.ecommerce.server.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prodyna.ecommerce.server.exception.EntityNotFoundException;
import com.prodyna.ecommerce.server.repository.entity.*;
import com.prodyna.ecommerce.server.resource.dto.CustomerDto;
import com.prodyna.ecommerce.server.resource.dto.OrderDetailsDto;
import com.prodyna.ecommerce.server.resource.dto.OrderDto;
import com.prodyna.ecommerce.server.services.OrderService;
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
public class OrderResourceSpringTest {

    private final static String TEST_ID = "22";
    private final static String TEST_NAME = "Product name";
    private final static String TEST_DESCRIPTION = "Product desc";
    private final static String TEST_PHOTO_URL = "/test/url/bla.jpg";
    private final static Category TEST_CATEGORY = Category.builder().categoryId(TEST_ID).name(TEST_NAME).build();
    private final static Product TEST_PRODUCT = Product.builder().productId(TEST_ID).name(TEST_NAME).description(TEST_DESCRIPTION)
            .photoUrl(TEST_PHOTO_URL).category(TEST_CATEGORY).active(true).build();
    private final static OrderDetails TEST_ORDER_DETAILS = OrderDetails.builder().ordinal(14d).product(TEST_PRODUCT).build();

    private final static Customer TEST_CUSTOMER = Customer.builder().customerId(TEST_ID).active(true).name("Test customer").build();

    private final static Order TEST_ORDER = Order.builder().orderId(TEST_ID).customer(TEST_CUSTOMER).
            orderStatus(OrderStatus.ACCEPTED).orderDetails(Collections.singletonList(TEST_ORDER_DETAILS)).build();

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ConversionService conversionService;

    @MockBean
    private OrderService orderService;

    private MockMvc mockMvc;

    @Before
    public void setupMvcMock() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void loadOrderReturnsProperResourceAndStatusOk() throws Exception {
        doReturn(TEST_ORDER)
                .when(orderService)
                .load(anyString());

        mockMvc.perform(get(OrderResource.createResourceSingleLink(TEST_ID).toString()).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(TEST_ID))
                .andExpect(jsonPath("$.customer.customerId").value(TEST_ORDER.getCustomer().getCustomerId()))
                .andExpect(jsonPath("$.customer.name").value(TEST_ORDER.getCustomer().getName()))
                .andExpect(jsonPath("$.customer.active").value(TEST_ORDER.getCustomer().getActive()))
                .andExpect(jsonPath("$.orderDetails[0].ordinal").value(TEST_ORDER_DETAILS.getOrdinal()))
                .andExpect(jsonPath("$.orderDetails[0].price").value(TEST_ORDER_DETAILS.getPrice()))
                .andExpect(jsonPath("$.orderDetails[0].quantity").value(TEST_ORDER_DETAILS.getQuantity()))
                .andExpect(jsonPath("$.orderDetails[0].amount").value(TEST_ORDER_DETAILS.getAmount()))
                .andExpect(jsonPath("$.orderDetails[0].product").exists());
    }

    @Test
    public void loadOrderReturnsNotFoundStatusOnServiceEntityNotFoundException() throws Exception {
        doThrow(EntityNotFoundException.class)
                .when(orderService)
                .load(anyString());

        mockMvc.perform(get(OrderResource.createResourceSingleLink(TEST_ID).toString()).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllReturnsProperResourceAndStatusOk() throws Exception {
        doReturn(Collections.singletonList(TEST_ORDER))
                .when(orderService)
                .getAll();

        mockMvc.perform(get(OrderResource.createResourceLink().toString()).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[0].orderId").value(TEST_ID))
                .andExpect(jsonPath("$[0].customer.customerId").value(TEST_ORDER.getCustomer().getCustomerId()))
                .andExpect(jsonPath("$[0].customer.name").value(TEST_ORDER.getCustomer().getName()))
                .andExpect(jsonPath("$[0].customer.active").value(TEST_ORDER.getCustomer().getActive()))
                .andExpect(jsonPath("$[0].orderDetails[0].ordinal").value(TEST_ORDER_DETAILS.getOrdinal()))
                .andExpect(jsonPath("$[0].orderDetails[0].price").value(TEST_ORDER_DETAILS.getPrice()))
                .andExpect(jsonPath("$[0].orderDetails[0].quantity").value(TEST_ORDER_DETAILS.getQuantity()))
                .andExpect(jsonPath("$[0].orderDetails[0].amount").value(TEST_ORDER_DETAILS.getAmount()))
                .andExpect(jsonPath("$[0].orderDetails[0].product").exists());
    }

    @Test
    public void deletePerformsProperActionAndStatusIsNoContent() throws Exception {
        mockMvc.perform(delete(OrderResource.createResourceSingleLink(TEST_ID).toString()).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNoContent());

        verify(orderService, times(1)).delete(eq(TEST_ID));
    }

    @Test
    public void insertReturnsProperResourceAndStatusIsOk() throws Exception {
        doReturn(TEST_ORDER)
                .when(orderService)
                .insert(any(Order.class));

        mockMvc.perform(post(OrderResource.createResourceLink().toString()).accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(TEST_ORDER)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(TEST_ID))
                .andExpect(jsonPath("$.customer.customerId").value(TEST_ORDER.getCustomer().getCustomerId()))
                .andExpect(jsonPath("$.customer.name").value(TEST_ORDER.getCustomer().getName()))
                .andExpect(jsonPath("$.customer.active").value(TEST_ORDER.getCustomer().getActive()))
                .andExpect(jsonPath("$.orderDetails[0].ordinal").value(TEST_ORDER_DETAILS.getOrdinal()))
                .andExpect(jsonPath("$.orderDetails[0].price").value(TEST_ORDER_DETAILS.getPrice()))
                .andExpect(jsonPath("$.orderDetails[0].quantity").value(TEST_ORDER_DETAILS.getQuantity()))
                .andExpect(jsonPath("$.orderDetails[0].amount").value(TEST_ORDER_DETAILS.getAmount()))
                .andExpect(jsonPath("$.orderDetails[0].product").exists());
    }

    @Test
    public void updateReturnsProperResourceAndStatusIsOk() throws Exception {
        doReturn(TEST_ORDER)
                .when(orderService)
                .update(any(Order.class));

        mockMvc.perform(post(OrderResource.createResourceLink().toString()  + "/update").accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(TEST_ORDER)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(TEST_ID))
                .andExpect(jsonPath("$.customer.customerId").value(TEST_ORDER.getCustomer().getCustomerId()))
                .andExpect(jsonPath("$.customer.name").value(TEST_ORDER.getCustomer().getName()))
                .andExpect(jsonPath("$.customer.active").value(TEST_ORDER.getCustomer().getActive()))
                .andExpect(jsonPath("$.orderDetails[0].ordinal").value(TEST_ORDER_DETAILS.getOrdinal()))
                .andExpect(jsonPath("$.orderDetails[0].price").value(TEST_ORDER_DETAILS.getPrice()))
                .andExpect(jsonPath("$.orderDetails[0].quantity").value(TEST_ORDER_DETAILS.getQuantity()))
                .andExpect(jsonPath("$.orderDetails[0].amount").value(TEST_ORDER_DETAILS.getAmount()))
                .andExpect(jsonPath("$.orderDetails[0].product").exists());
    }

    @Test
    public void conversionServiceIsAvailableAndConvertsEntityToDto() {
        assertThat(conversionService).isNotNull();
        assertThat(conversionService.canConvert(Customer.class, CustomerDto.class)).isTrue();
        assertThat(conversionService.canConvert(OrderDetails.class, OrderDetailsDto.class)).isTrue();
        assertThat(conversionService.canConvert(Order.class, OrderDto.class)).isTrue();

        assertThat(conversionService.canConvert(CustomerDto.class, Customer.class)).isTrue();
        assertThat(conversionService.canConvert(OrderDetailsDto.class, OrderDetails.class)).isTrue();
        assertThat(conversionService.canConvert(OrderDto.class, Order.class)).isTrue();
    }

    @Test
    public void resourceLinkAndResourceSingleLinkAreCreated() {
        Link resourcesLink = OrderResource.createResourceLink().withSelfRel();
        Link singleResourceLink = OrderResource.createResourceSingleLink("13").withSelfRel();
        assertThat(resourcesLink.getHref()).endsWith("/orders");
        assertThat(singleResourceLink.getHref()).endsWith("/orders/13");
    }

}
