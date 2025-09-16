package com.gtelant.commerce_service.dtos;

import lombok.Data;

@Data
public class ItemRequest {
    private int productId;
    private int amount;
}
