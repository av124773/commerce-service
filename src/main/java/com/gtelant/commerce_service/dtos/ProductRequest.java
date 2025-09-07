package com.gtelant.commerce_service.dtos;

import com.gtelant.commerce_service.models.Category;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductRequest {
    private String reference;
    private double width;
    private double height;
    private int categoryId;
    private BigDecimal price;
    private int stock;
    private int sales;
    private String description;
    private String imageUrl;
    private String thumbnailUrl;
    private LocalDateTime lastUpdateAt;

}
