package com.gtelant.commerce_service.responses;

import com.gtelant.commerce_service.models.User;

public class UpdateUserResponse {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String birthday;
    private boolean hasNewsletter;

    public UpdateUserResponse() {
    }
    
    public UpdateUserResponse(String firstName, String lastName, String address, String city, String state,
            String zipcode, String birthday, boolean hasNewsletter) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.birthday = birthday;
        this.hasNewsletter = hasNewsletter;
    }

    public UpdateUserResponse(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.city = user.getCity();
        this.state = user.getState();
        this.zipcode = user.getZipcode();
        this.birthday = user.getBirthday().toString();
        this.hasNewsletter = user.isHasNewsletter();
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

    
}
