package com.gtelant.commerce_service.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReviewResponse {
    private int id;
    private int rating;
    private String comment;
    private int userId;
    private int productId;
    private int statusId;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
