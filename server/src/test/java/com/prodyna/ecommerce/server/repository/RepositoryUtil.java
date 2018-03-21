package com.prodyna.ecommerce.server.repository;

import com.prodyna.ecommerce.server.repository.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryUtil {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CostCenterRepository costCenterRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public void cleanDb() {
        categoryRepository.deleteAll();
        costCenterRepository.deleteAll();
        customerRepository.deleteAll();
        productRepository.deleteAll();
        orderRepository.deleteAll();
    }
}
