package com.gtelant.commerce_service.dtos;

import lombok.Data;

@Data
public class ItemResponse {
    private int id;
    private int orderId;
    private int productId;
    private String productName;
    private int amount;
    private java.math.BigDecimal price;
    private java.time.LocalDateTime createdAt;
}
