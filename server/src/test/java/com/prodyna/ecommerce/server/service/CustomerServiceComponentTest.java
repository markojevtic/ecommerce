package com.prodyna.ecommerce.server.service;

import com.prodyna.ecommerce.server.exception.EntityNotFoundException;
import com.prodyna.ecommerce.server.repository.ComponentTest;
import com.prodyna.ecommerce.server.repository.CustomerRepository;
import com.prodyna.ecommerce.server.repository.entity.Address;
import com.prodyna.ecommerce.server.repository.entity.Customer;
import com.prodyna.ecommerce.server.services.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceComponentTest extends ComponentTest {

    private final static String CITY_NAME = "Beograd";
    private final static String ZIP = "11000";
    private final static String ADDRESS = "Ludacka 13";
    private final static String CUSTOMER1_ID = "556";
    private final static String CUSTOMER2_ID = "123";
    private final static String POOR_NAME = "Poor customer";
    private final static String RICH_NAME = "Rich customer";


    private final static Address TEST_ADDRESS = Address.builder().city(CITY_NAME).zip(ZIP).streetAndNumber(ADDRESS).build();
    private final static Customer TEST_CUSTOMER1 = Customer.builder().customerId(CUSTOMER1_ID).name(RICH_NAME).address(TEST_ADDRESS)
            .build();

    private final static Customer TEST_CUSTOMER2 = Customer.builder().customerId(CUSTOMER2_ID).name(POOR_NAME).address(TEST_ADDRESS)
            .active(true).build();

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    // izmeni metode po uzoru na test za costcener. Treba da se oslanjas na mongo repozitorijum a ne na svoj servis

    @Test
    public void insertCustomerReturnsProperEntity() {
        customerService.insert(TEST_CUSTOMER1);
        Customer retrievedCustomer = customerRepository.findById(TEST_CUSTOMER1.getCustomerId()).orElse(null);

        assertThat(retrievedCustomer).isNotNull();
        assertThat(retrievedCustomer).isEqualTo(TEST_CUSTOMER1);
    }

    @Test(expected = DuplicateKeyException.class)
    public void insertDuplicateCustomerThrowsException() {
        customerService.insert(TEST_CUSTOMER1);
        customerService.insert(TEST_CUSTOMER1);
    }

    @Test
    public void updateCustomerReturnsProperEntity() {
        customerService.insert(TEST_CUSTOMER1);
        Customer retrievedCustomer = customerRepository.findById(TEST_CUSTOMER1.getCustomerId()).orElse(null);
        assertThat(retrievedCustomer).isNotNull();
        assertThat(retrievedCustomer.getActive()).isNull();

        TEST_CUSTOMER1.setActive(true);
        customerService.update(TEST_CUSTOMER1);
        retrievedCustomer = customerRepository.findById(TEST_CUSTOMER1.getCustomerId()).orElse(null);
        assertThat(retrievedCustomer.getActive()).isTrue();
    }

    @Test
    public void getAllCustomersReturnsAllInsertedEntities() {
        customerService.insert(TEST_CUSTOMER1);
        customerService.insert(TEST_CUSTOMER2);

        List<Customer> customers = customerService.getAll();
        assertThat(customers).isNotNull();
        assertThat(customers).isNotEmpty();
        assertThat(customers.size()).isEqualTo(2);
        assertThat(customers.contains(TEST_CUSTOMER1));
        assertThat(customers.contains(TEST_CUSTOMER2));
    }

    @Test
    public void deleteRemovesProperEntity() {
        customerService.insert(TEST_CUSTOMER2);
        customerService.delete(CUSTOMER2_ID);

        Customer customer = customerRepository.findById(CUSTOMER2_ID).orElse(null);
        assertThat(customer).isNull();
    }

    @Test
    public void loadReturnsProperEntity() {
        customerService.insert(TEST_CUSTOMER1);
        Customer loadedCustomer = customerService.load(CUSTOMER1_ID);

        assertThat(loadedCustomer).isEqualTo(TEST_CUSTOMER1);
    }

    @Test(expected = EntityNotFoundException.class)
    public void loadNonexistentCustomerThrowsException() {
        customerService.load(CUSTOMER2_ID);
    }
}
