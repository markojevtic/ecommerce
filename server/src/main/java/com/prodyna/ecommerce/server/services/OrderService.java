package com.prodyna.ecommerce.server.services;

import com.prodyna.ecommerce.server.repository.entity.Order;

import java.util.List;

public interface OrderService {

    Order load(String id);

    Order insert(Order order);

    Order update(Order order);

    void delete(String id);

    List<Order> getAll();
}
