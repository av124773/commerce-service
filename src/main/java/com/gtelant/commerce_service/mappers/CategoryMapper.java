package com.gtelant.commerce_service.mappers;

import com.gtelant.commerce_service.dtos.CategoryRequest;
import com.gtelant.commerce_service.dtos.CategoryResponse;
import com.gtelant.commerce_service.models.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse (Category category) {
        CategoryResponse dto = new CategoryResponse();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setCreatedAt(category.getCreatedAt());
        dto.setLastUpdateAt(category.getLastUpdateAt());
        return dto;
    }

    public Category updateEntity(Category category, CategoryRequest request) {
        if (request.getName() != null) {
            category.setName(request.getName());
        }
        if (request.getLastUpdateAt() != null) {
            category.setLastUpdateAt(request.getLastUpdateAt());
        }
        if (request.getDeleteAt() != null) {
            category.setDeleteAt(request.getDeleteAt());
        }
        return category;
    }
}
