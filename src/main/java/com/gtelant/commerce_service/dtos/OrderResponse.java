package com.gtelant.commerce_service.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.gtelant.commerce_service.models.OrderStatus;

import lombok.Data;

@Data
public class OrderResponse {
    private int id;
    private String reference;
    private UserResponse user;
    private OrderStatus orderStatus;
    private String shippingAddress;
    private BigDecimal delivery;
    private BigDecimal tax;
    private boolean isReturned;
    private LocalDateTime createdAt;
    private List<ItemResponse> items;
}
