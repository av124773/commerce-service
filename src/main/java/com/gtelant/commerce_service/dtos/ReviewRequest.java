package com.gtelant.commerce_service.dtos;

import lombok.Data;

@Data
public class ReviewRequest {
    private int rating;
    private String comment;
    private int userId;
    private int productId;
}
