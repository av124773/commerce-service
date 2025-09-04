package com.gtelant.commerce_service.mappers;

import com.gtelant.commerce_service.dtos.UserSegmentResponse;
import com.gtelant.commerce_service.models.UserSegment;
import org.springframework.stereotype.Component;

import com.gtelant.commerce_service.dtos.UserRequest;
import com.gtelant.commerce_service.dtos.UserResponse;
import com.gtelant.commerce_service.models.User;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    
    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();

        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setAddress(user.getAddress());
        response.setBirthday(user.getBirthday());
        response.setCity(user.getCity());
        response.setState(user.getState());
        response.setZipcode(user.getZipcode());
        response.setRole(user.getRole());
        response.setHasNewsletter(user.isHasNewsletter());
        response.setLastsSeemAt(user.getLastsSeemAt());
        response.setCreatedAt(user.getCreatedAt());
        response.setDeleteAt(user.getDeleteAt());
        if (user.getUserSegments() != null) {
            response.setUserSegments(user.getUserSegments().stream()
                    .map(this::toUserSegmentResponse)
                    .collect(Collectors.toList()));
        }

        return response;
    }

    public UserSegmentResponse toUserSegmentResponse(UserSegment userSegment) {
        UserSegmentResponse dto = new UserSegmentResponse();
        dto.setId(userSegment.getId());
        dto.setUserId(userSegment.getUser().getId());
        dto.setSegmentId(userSegment.getSegment().getId());
        dto.setName(userSegment.getSegment().getName());
        dto.setCreatedAt(userSegment.getCreatedAt());
        return dto;
    }

    public User toEntity(UserRequest request) {
        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setBirthday(request.getBirthday());
        user.setAddress(request.getAddress());
        user.setCity(request.getCity());
        user.setState(request.getState());
        user.setZipcode(request.getZipcode());
        user.setRole(request.getRole());
        user.setPassword(request.getPassword());
        user.setHasNewsletter(request.isHasNewsletter());

        return user;
    }

    public User updateEntity(User user, UserRequest request) {
        if (request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getBirthday() != null) {
            user.setBirthday(request.getBirthday());
        }
        if (request.getAddress() != null) {
            user.setAddress(request.getAddress());
        }
        if (request.getCity() != null) {
            user.setCity(request.getCity());
        }
        if (request.getState() != null) {
            user.setState(request.getState());
        }
        if (request.getZipcode() != null) {
            user.setZipcode(request.getZipcode());
        }
        if (request.getPassword() != null) {
            user.setPassword(request.getPassword());
        }
        if (request.isHasNewsletter() != user.isHasNewsletter()) {
            user.setHasNewsletter(request.isHasNewsletter());
        }

        return user;
    }


}
