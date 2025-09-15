package com.gtelant.commerce_service.dtos;

import java.time.LocalDateTime;

import com.gtelant.commerce_service.enums.ReviewStatus;

import lombok.Data;

@Data
public class ReviewResponse {
    private Integer id;
    private int rating;
    private String comment;
    private int userId;
    private String userName;
    private int productId;
    private String productName;
    private ReviewStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
