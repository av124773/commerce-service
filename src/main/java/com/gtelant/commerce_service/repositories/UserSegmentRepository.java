package com.gtelant.commerce_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtelant.commerce_service.models.UserSegment;

public interface UserSegmentRepository extends JpaRepository<UserSegment, Integer> {
    
}
