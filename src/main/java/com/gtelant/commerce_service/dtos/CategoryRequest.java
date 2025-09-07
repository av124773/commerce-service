package com.gtelant.commerce_service.dtos;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
public class CategoryRequest {
    private String name;
    private LocalDateTime lastUpdateAt;
    private LocalDateTime deleteAt;
}
