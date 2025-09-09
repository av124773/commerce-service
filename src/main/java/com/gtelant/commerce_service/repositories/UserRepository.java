package com.gtelant.commerce_service.repositories;

import com.gtelant.commerce_service.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    Page<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
        String firstName, 
        String lastName, 
        PageRequest pageRequest
    );
    Page<User> findByUserSegments_Segment_IdAndFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
        Integer segmentId, 
        String firstName, 
        String lastName, 
        PageRequest pageRequest
    );
}
