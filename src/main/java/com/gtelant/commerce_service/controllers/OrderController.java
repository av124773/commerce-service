package com.gtelant.commerce_service.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gtelant.commerce_service.dtos.OrderResponse;
import com.gtelant.commerce_service.mappers.OrderMapper;
import com.gtelant.commerce_service.models.Order;
import com.gtelant.commerce_service.services.OrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }
    
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders().stream().map(orderMapper::toResponse).toList();
    }

}
