package com.gtelant.commerce_service.repositories;

import com.gtelant.commerce_service.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Page<User> findByFirstNameContainingIgnoreCase(String query, PageRequest pageRequest);
}
