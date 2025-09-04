package com.gtelant.commerce_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtelant.commerce_service.models.Segment;

public interface SegmentRepository extends JpaRepository<Segment, Integer> {
    
}
