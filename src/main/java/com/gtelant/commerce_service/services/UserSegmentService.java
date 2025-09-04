package com.gtelant.commerce_service.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gtelant.commerce_service.models.UserSegment;
import com.gtelant.commerce_service.repositories.UserSegmentRepository;

@Service
public class UserSegmentService {

    private final UserSegmentRepository userSegmentRepository;

    public UserSegmentService(UserSegmentRepository userSegmentRepository) {
        this.userSegmentRepository = userSegmentRepository;
    }

    public List<UserSegment> getAllUserSegments() {
        return userSegmentRepository.findAll();
    }

    public UserSegment saveUserSegment(UserSegment userSegment) {
        return userSegmentRepository.save(userSegment);
    }
    
}
