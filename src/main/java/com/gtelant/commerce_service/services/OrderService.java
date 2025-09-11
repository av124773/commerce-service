package com.gtelant.commerce_service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gtelant.commerce_service.models.Order;
import com.gtelant.commerce_service.repositories.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(int id) {
        return orderRepository.findById(id);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }
    
}
