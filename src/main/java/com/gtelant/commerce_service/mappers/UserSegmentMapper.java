package com.gtelant.commerce_service.mappers;

import org.springframework.stereotype.Component;

import com.gtelant.commerce_service.dtos.UserSegmentResponse;
import com.gtelant.commerce_service.models.Segment;
import com.gtelant.commerce_service.models.User;
import com.gtelant.commerce_service.models.UserSegment;

@Component
public class UserSegmentMapper {
    public UserSegmentResponse toResponse(UserSegment userSegment) {
        UserSegmentResponse response = new UserSegmentResponse();

        response.setId(userSegment.getId());
        response.setUserId(userSegment.getUser().getId());
        response.setSegmentId(userSegment.getSegment().getId());
        response.setCreatedAt(userSegment.getCreatedAt());
        response.setDeletedAt(userSegment.getDeletedAt());

        return response;
    }

    public UserSegment toEntity(User user, Segment segment) {
        UserSegment userSegment = new UserSegment();

        userSegment.setUser(user);
        userSegment.setSegment(segment);

        return userSegment;
    }
    
}
