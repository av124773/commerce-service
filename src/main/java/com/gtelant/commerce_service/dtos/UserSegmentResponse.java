package com.gtelant.commerce_service.dtos;

import java.time.LocalDateTime;

import com.gtelant.commerce_service.models.Segment;
import com.gtelant.commerce_service.models.User;

import lombok.Data;

@Data
public class UserSegmentResponse {
    private int id;
    private int userId;
    private int segmentId;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
