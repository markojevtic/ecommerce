package com.prodyna.ecommerce.server.services;

import com.prodyna.ecommerce.server.repository.entity.User;

import java.util.List;

public interface UserService {
    User load(String username);

    List<User> getAll();

    User insert(User user);

    User update(User user);

    void delete(String username);
}
