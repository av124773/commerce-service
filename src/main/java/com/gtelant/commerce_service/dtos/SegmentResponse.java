package com.gtelant.commerce_service.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SegmentResponse {
    private Integer id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
