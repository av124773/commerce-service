package com.gtelant.commerce_service.requests;

import com.gtelant.commerce_service.models.User;

import jakarta.validation.constraints.Pattern;

public class UpdateUserRequest {
    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private String zipcode;
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Birthday must be in format YYYY-MM-DD")
    private String birthday;
    private boolean hasNewsletter;
    private String address;

    public UpdateUserRequest() {
    }
    
    public UpdateUserRequest(String firstName, String lastName, String city, 
                            String state, String zipcode, String birthday, 
                            boolean hasNewsletter, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.birthday = birthday;
        this.hasNewsletter = hasNewsletter;
        this.address = address;
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
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public boolean isHasNewsletter() {
        return hasNewsletter;
    }
    public void setHasNewsletter(boolean hasNewsletter) {
        this.hasNewsletter = hasNewsletter;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    
}
