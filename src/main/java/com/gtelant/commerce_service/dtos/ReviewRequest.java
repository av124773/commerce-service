package com.gtelant.commerce_service.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReviewRequest {
    private int rating;
    private String comment;
    private int userId;
    private int productId;
    private int statusId;
    private LocalDateTime deletedAt;
}
