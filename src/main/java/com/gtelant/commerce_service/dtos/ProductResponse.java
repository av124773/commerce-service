package com.gtelant.commerce_service.dtos;

import com.gtelant.commerce_service.models.Category;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductResponse {
    private int id;
    private String reference;
    private double width;
    private double height;
    private BigDecimal price;
    private int stock;
    private int sales;
    private String description;
    private String imageUrl;
    private String thumbnailUrl;
    private LocalDateTime lastUpdateAt;
    private CategoryResponse category;

}
