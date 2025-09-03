package com.gtelant.commerce_service.mappers;

import org.springframework.stereotype.Component;

import com.gtelant.commerce_service.dtos.UserRequest;
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


}
