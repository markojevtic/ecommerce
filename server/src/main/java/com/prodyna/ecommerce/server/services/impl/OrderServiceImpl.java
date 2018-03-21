package com.prodyna.ecommerce.server.services.impl;

import com.prodyna.ecommerce.server.exception.EntityNotFoundException;
import com.prodyna.ecommerce.server.repository.OrderRepository;
import com.prodyna.ecommerce.server.repository.entity.Order;
import com.prodyna.ecommerce.server.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order insert(Order order) {
        return orderRepository.insert(order);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order load(String id) {
        return orderRepository.findById(id).orElseThrow(() -> EntityNotFoundException.createExceptionByEntityAndId(Order.class, id));
    }

    @Override
    public void delete(String id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
