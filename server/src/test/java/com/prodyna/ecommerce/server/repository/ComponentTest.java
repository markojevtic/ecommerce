package com.prodyna.ecommerce.server.repository;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class ComponentTest {

    @Autowired
    protected RepositoryUtil repositoryUtil;

    @Before
    public void init() {
        repositoryUtil.cleanDb();
    }
}
