package com.gtelant.commerce_service.mappers;

import com.gtelant.commerce_service.dtos.CategoryResponse;
import com.gtelant.commerce_service.dtos.ProductRequest;
import com.gtelant.commerce_service.dtos.ProductResponse;
import com.gtelant.commerce_service.models.Category;
import com.gtelant.commerce_service.models.Product;
import com.gtelant.commerce_service.services.CategoryService;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;

    public ProductMapper(CategoryMapper categoryMapper, CategoryService categoryService) {
        this.categoryMapper = categoryMapper;
        this.categoryService = categoryService;
    }

    public ProductResponse toResponse(Product product) {
        ProductResponse dto = new ProductResponse();
        dto.setId(product.getId());
        dto.setReference(product.getReference());
        dto.setWidth(product.getWidth());
        dto.setHeight(product.getHeight());
        dto.setPrice(product.getPrice());
        dto.setSales(product.getSales());
        dto.setStock(product.getStock());
        dto.setDescription(product.getDescription());
        dto.setImageUrl(product.getImageUrl());
        dto.setThumbnailUrl(product.getThumbnailUrl());
        dto.setLastUpdateAt(product.getLastUpdateAt());
        if (product.getCategory() != null) {
            CategoryResponse response = categoryMapper.toResponse(product.getCategory());
            dto.setCategory(response);
        }

        return dto;
    }

    public Product toEntity(ProductRequest request) {
        Product dto = new Product();
        dto.setReference(request.getReference());
        dto.setWidth(request.getWidth());
        dto.setHeight(request.getHeight());
        dto.setPrice(request.getPrice());
        dto.setStock(request.getStock());
        dto.setStock(request.getStock());
        dto.setDescription(request.getDescription());
        dto.setImageUrl(request.getImageUrl());
        dto.setThumbnailUrl(request.getThumbnailUrl());
        dto.setLastUpdateAt(request.getLastUpdateAt());

        Optional<Category> category = categoryService.getCategoryById(request.getCategoryId());
        if (category.isPresent()) {
            dto.setCategory(category.get());
        }

        return dto;
    }

    public Product updateEntity(Product product, ProductRequest request) {
        if (request.getReference() != null) {
            product.setReference(request.getReference());
        }
        if (request.getWidth() != product.getWidth()) {
            product.setWidth(request.getWidth());
        }
        if (request.getHeight() != product.getHeight()) {
            product.setHeight(request.getHeight());
        }
        if (request.getPrice() != null) {
            product.setPrice(request.getPrice());
        }
        if (request.getStock() != product.getStock()) {
            product.setStock(request.getStock());
        }
        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }
        if (request.getImageUrl() != null) {
            product.setImageUrl(request.getImageUrl());
        }
        if (request.getThumbnailUrl() != null) {
            product.setThumbnailUrl(request.getThumbnailUrl());
        }
        if (request.getLastUpdateAt() != null) {
            product.setLastUpdateAt(request.getLastUpdateAt());
        }
        if (request.getCategoryId() != product.getCategory().getId()) {
            Optional<Category> category = categoryService.getCategoryById(request.getCategoryId());
            category.ifPresent(product::setCategory);
        }
        return product;
    }
}
