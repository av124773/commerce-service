package com.gtelant.commerce_service.mappers;

import org.springframework.stereotype.Component;

import com.gtelant.commerce_service.dtos.SegmentRequest;
import com.gtelant.commerce_service.dtos.SegmentResponse;
import com.gtelant.commerce_service.models.Segment;

@Component
public class SegmentMapper {

    public SegmentResponse toResponse(Segment segment) {
        SegmentResponse response = new SegmentResponse();

        response.setId(segment.getId());
        response.setName(segment.getName());
        response.setCreatedAt(segment.getCreatedAt());
        response.setDeletedAt(segment.getDeletedAt());

        return response;
    }

    public Segment toEntity(SegmentRequest request) {
        Segment segment = new Segment();

        segment.setName(request.getName());

        return segment;

    }

    public Segment updateEntity(Segment segment, SegmentRequest request) {
        if (request.getName() != null) {
            segment.setName(request.getName());
        }
        return segment;
    }
    
}
