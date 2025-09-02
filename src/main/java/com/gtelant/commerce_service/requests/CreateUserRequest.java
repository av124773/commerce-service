package com.gtelant.commerce_service.requests;

import jakarta.validation.constraints.Pattern;

public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Birthday must be in format YYYY-MM-DD")
    private String birthday; // Changed to String to match JSON format
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String password;
    private String role;
    private boolean hasNewsletter;
    
    public CreateUserRequest() {
    }

    public CreateUserRequest(String firstName, String lastName, String email, String birthday, String address,
            String city, String state, String zipcode, String password, String role, boolean hasNewsletter) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.password = password;
        this.role = role;
        this.hasNewsletter = hasNewsletter;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getZipcode() {
        return zipcode;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public boolean isHasNewsletter() {
        return hasNewsletter;
    }
    public void setHasNewsletter(boolean hasNewsletter) {
        this.hasNewsletter = hasNewsletter;
    }
    
}
