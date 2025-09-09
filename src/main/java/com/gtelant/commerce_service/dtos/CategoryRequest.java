package com.gtelant.commerce_service.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryRequest {
    private String name;
    private LocalDateTime lastUpdateAt;
}
