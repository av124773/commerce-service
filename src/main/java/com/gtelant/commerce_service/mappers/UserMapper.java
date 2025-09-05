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
        UserResponse dto = new UserResponse();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setAddress(user.getAddress());
        dto.setBirthday(user.getBirthday());
        dto.setCity(user.getCity());
        dto.setState(user.getState());
        dto.setZipcode(user.getZipcode());
        dto.setRole(user.getRole());
        dto.setHasNewsletter(user.isHasNewsletter());
        dto.setLastsSeemAt(user.getLastsSeemAt());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setDeleteAt(user.getDeleteAt());
        if (user.getUserSegments() != null) {
            dto.setUserSegments(user.getUserSegments().stream()
                    .map(this::toUserSegmentResponse)
                    .collect(Collectors.toList()));
        }
        return dto;
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
        User dto = new User();
        dto.setFirstName(request.getFirstName());
        dto.setLastName(request.getLastName());
        dto.setEmail(request.getEmail());
        dto.setBirthday(request.getBirthday());
        dto.setAddress(request.getAddress());
        dto.setCity(request.getCity());
        dto.setState(request.getState());
        dto.setZipcode(request.getZipcode());
        dto.setRole(request.getRole());
        dto.setPassword(request.getPassword());
        dto.setHasNewsletter(request.isHasNewsletter());
        return dto;
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
