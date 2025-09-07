package com.gtelant.commerce_service.dtos;

import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
public class CategoryResponse {
    private int id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdateAt;
}
