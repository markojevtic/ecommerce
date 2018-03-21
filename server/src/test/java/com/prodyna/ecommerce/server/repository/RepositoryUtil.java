package com.prodyna.ecommerce.server.repository;

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

    public void cleanDb() {
        categoryRepository.deleteAll();
        costCenterRepository.deleteAll();
        customerRepository.deleteAll();
    }
}
