package com.gtelant.commerce_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtelant.commerce_service.models.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    
}
