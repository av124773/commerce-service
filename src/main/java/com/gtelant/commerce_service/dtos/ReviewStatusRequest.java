package com.gtelant.commerce_service.dtos;

import lombok.Data;

import java.util.List;

import com.gtelant.commerce_service.enums.ReviewStatus;

@Data
public class ReviewStatusRequest {
    private List<Integer> ids;
    private ReviewStatus reviewStatus;
}
