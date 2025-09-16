package com.gtelant.commerce_service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gtelant.commerce_service.models.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>, JpaSpecificationExecutor<Item> {
    List<Item> findAllByOrderReference(String orderReference);
}
