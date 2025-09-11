package com.gtelant.commerce_service.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReviewResponse {
    private int id;
    private int rating;
    private String comment;
    private int userId;
    private String userName;
    private int productId;
    private String productName;
    private int statusId;
    private String statusName;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
