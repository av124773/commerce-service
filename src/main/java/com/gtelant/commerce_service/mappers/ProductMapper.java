package com.gtelant.commerce_service.mappers;

import com.gtelant.commerce_service.dtos.CategoryResponse;
import com.gtelant.commerce_service.dtos.ProductRequest;
import com.gtelant.commerce_service.dtos.ProductResponse;
import com.gtelant.commerce_service.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final CategoryMapper categoryMapper;

    public ProductMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
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
    /*
    private String reference;
    private double width;
    private double height;
    private int categoryId;
    private BigDecimal price;
    private int stock;
    private int sales;
    private String description;
    private String imageUrl;
    private String thumbnailUrl;
    private LocalDateTime lastUpdateAt;
    * */
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

        // todo
//        dto.setCategory();
        return dto;
    }
}
