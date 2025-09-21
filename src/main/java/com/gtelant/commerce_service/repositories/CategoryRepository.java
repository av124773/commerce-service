package com.gtelant.commerce_service.repositories;

import com.gtelant.commerce_service.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public Category findByName(String name);
}
