package com.gtelant.commerce_service.mappers;

import org.springframework.stereotype.Component;

import com.gtelant.commerce_service.dtos.UserResponse;
import com.gtelant.commerce_service.models.User;

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
        response.setUserSegments(user.getUserSegments());

        return response;
    }


}
