package com.prodyna.ecommerce.server.services.impl;

import com.prodyna.ecommerce.server.repository.entity.User;
import com.prodyna.ecommerce.server.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User load(String username) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User insert(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(String username) {

    }
}
