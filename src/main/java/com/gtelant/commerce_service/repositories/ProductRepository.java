package com.gtelant.commerce_service.repositories;

import com.gtelant.commerce_service.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
