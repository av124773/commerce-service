package com.gtelant.commerce_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtelant.commerce_service.models.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {
    
}
