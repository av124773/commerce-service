package com.gtelant.commerce_service.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryResponse {
    private int id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdateAt;
}
