package com.gtelant.commerce_service.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderStatusResponse {
    private int id;
    private String name;
    private LocalDateTime createdAt;
}
