package com.gtelant.commerce_service.dtos;

import lombok.Data;

@Data
public class UserSegmentRequest {
    private int userId;
    private int segmentId;
}
