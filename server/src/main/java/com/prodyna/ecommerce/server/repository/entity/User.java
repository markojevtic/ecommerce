package com.prodyna.ecommerce.server.repository.entity;

import java.util.List;

public class User {
    private String username;
    private String fullName;
    private Customer customer;
    private boolean active;
    private List<Role> roles;
}
