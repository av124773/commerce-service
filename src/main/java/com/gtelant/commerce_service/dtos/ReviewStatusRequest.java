package com.gtelant.commerce_service.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReviewStatusRequest {
    private List<Integer> ids;
    private int statusId;
}
