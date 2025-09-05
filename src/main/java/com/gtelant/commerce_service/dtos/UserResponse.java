package com.gtelant.commerce_service.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserResponse {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String role;
    private boolean hasNewsletter;
    private LocalDateTime lastsSeemAt;
    private LocalDateTime createdAt;
    private LocalDateTime deleteAt;
    private List<UserSegmentResponse> userSegments;
}
