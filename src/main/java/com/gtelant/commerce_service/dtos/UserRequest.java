package com.gtelant.commerce_service.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String role;
    private String password;
    private boolean hasNewsletter;
}
