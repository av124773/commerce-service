package com.gtelant.commerce_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtelant.commerce_service.models.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    
}
