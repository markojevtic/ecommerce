package com.prodyna.ecommerce.server.service;

import com.prodyna.ecommerce.server.exception.EntityNotFoundException;
import com.prodyna.ecommerce.server.repository.ComponentTest;
import com.prodyna.ecommerce.server.repository.OrderRepository;
import com.prodyna.ecommerce.server.repository.entity.*;
import com.prodyna.ecommerce.server.services.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceComponentTest extends ComponentTest {

    private final static String ID = "456";
    private static final String ORDER_BELGRADE_ID = "159951";
    private final static Date DATE = new GregorianCalendar().getTime();
    private final static OrderStatus ORDER_STATUS = OrderStatus.DELIVERED;

    private final static String CITY_NAME = "Beograd";
    private final static String ZIP = "11000";
    private final static String ADDRESS = "Ludacka 13";
    private final static String CUSTOMER_ID = "556";
    private final static String RICH_NAME = "Rich customer";

    private static final String PRODUCT_NAME = "Belgrade product";
    private static final String PRODUCT_ID = "123";
    private final static Product TEST_PRODUCT = Product.builder().productId(PRODUCT_ID).name(PRODUCT_NAME).build();
    private final static OrderDetails TEST_ORDER_DETAILS = OrderDetails.builder().ordinal(14d).product(TEST_PRODUCT).build();

    private final static Address TEST_ADDRESS = Address.builder().city(CITY_NAME).zip(ZIP).streetAndNumber(ADDRESS).build();
    private final static Customer TEST_CUSTOMER = Customer.builder().customerId(CUSTOMER_ID).name(RICH_NAME).address(TEST_ADDRESS)
            .build();

    private final static Order TEST_ORDER = Order.builder().orderId(ID).orderDate(DATE).orderStatus(ORDER_STATUS)
            .orderDetails(Collections.singletonList(TEST_ORDER_DETAILS)).build();
    private static final Order TEST_ORDER_BELGRADE = Order.builder().orderId(ORDER_BELGRADE_ID).orderStatus(ORDER_STATUS).build();

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void insertOrderPersistsProperEntity() {
        orderService.insert(TEST_ORDER);
        Order retrievedOrder = orderRepository.findById(TEST_ORDER.getOrderId()).orElse(null);

        assertThat(retrievedOrder).isNotNull();
        assertThat(retrievedOrder).isEqualTo(TEST_ORDER);
    }

    @Test(expected = DuplicateKeyException.class)
    public void insertDuplicateOrderThrowsException() {
        orderService.insert(TEST_ORDER);
        orderService.insert(TEST_ORDER);
    }

    @Test
    public void updateOrderAltersProperEntity() {
        orderService.insert(TEST_ORDER);
        Order retrievedOrder = orderRepository.findById(TEST_ORDER.getOrderId()).orElse(null);
        assertThat(retrievedOrder.getCustomer()).isNull();

        TEST_ORDER.setCustomer(TEST_CUSTOMER);
        orderService.update(TEST_ORDER);
        retrievedOrder = orderRepository.findById(TEST_ORDER.getOrderId()).orElse(null);
        assertThat(retrievedOrder.getCustomer()).isEqualTo(TEST_ORDER.getCustomer());
    }

    @Test
    public void getAllOrdersReturnsAllInsertedEntities() {
        orderService.insert(TEST_ORDER);
        orderService.insert(TEST_ORDER_BELGRADE);

        List<Order> orders = orderService.getAll();
        assertThat(orders).isNotNull();
        assertThat(orders.size()).isEqualTo(2);
        assertThat(orders.contains(TEST_ORDER));
        assertThat(orders.contains(TEST_ORDER_BELGRADE));
    }

    @Test
    public void deleteRemovesProperOrder() {
        orderService.insert(TEST_ORDER);
        orderService.delete(TEST_ORDER.getOrderId());

        Order order = orderRepository.findById(TEST_ORDER.getOrderId()).orElse(null);
        assertThat(order).isNull();
    }

    @Test
    public void loadReturnsProperEntity() {
        orderService.insert(TEST_ORDER_BELGRADE);
        Order belgradeOrder = orderService.load(TEST_ORDER_BELGRADE.getOrderId());

        assertThat(belgradeOrder).isEqualTo(TEST_ORDER_BELGRADE);
    }

    @Test(expected = EntityNotFoundException.class)
    public void loadNonexistentOrderThrowsException() {
        orderService.load(TEST_ORDER_BELGRADE.getOrderId());
    }

}
