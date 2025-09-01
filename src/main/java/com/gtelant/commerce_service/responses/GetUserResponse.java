package com.gtelant.commerce_service.responses;

import com.gtelant.commerce_service.models.User;
import com.gtelant.commerce_service.models.UserSegment;

import java.time.LocalDateTime;
import java.util.List;

public class GetUserResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String role;
    private boolean hasNewsletter;
    private LocalDateTime lastsSeemAt;
    private List<UserSegment> userSegments;

    public GetUserResponse() {
    }

    public GetUserResponse(int id, String firstName, String lastName, String role, boolean hasNewsletter, LocalDateTime lastsSeemAt, List<UserSegment> userSegments) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.hasNewsletter = hasNewsletter;
        this.lastsSeemAt = lastsSeemAt;
    }

    public GetUserResponse(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.role = user.getRole();
        this.hasNewsletter = user.isHasNewsletter();
        this.lastsSeemAt = user.getLastsSeemAt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDateTime getLastsSeemAt() {
        return lastsSeemAt;
    }

    public void setLastsSeemAt(LocalDateTime lastsSeemAt) {
        this.lastsSeemAt = lastsSeemAt;
    }

    public List<UserSegment> getUserSegments() {
        return userSegments;
    }

    public void setUserSegments(List<UserSegment> userSegments) {
        this.userSegments = userSegments;
    }
}
