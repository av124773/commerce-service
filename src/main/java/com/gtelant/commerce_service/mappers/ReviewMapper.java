package com.gtelant.commerce_service.mappers;

import java.util.Optional;

import com.gtelant.commerce_service.enums.ReviewStatus;

import org.springframework.stereotype.Component;

import com.gtelant.commerce_service.dtos.ReviewRequest;
import com.gtelant.commerce_service.dtos.ReviewResponse;
import com.gtelant.commerce_service.models.Product;
import com.gtelant.commerce_service.models.Review;
import com.gtelant.commerce_service.models.User;
import com.gtelant.commerce_service.services.ProductService;
import com.gtelant.commerce_service.services.UserService;

@Component
public class ReviewMapper {
    private final UserService userService;
    private final ProductService productService;

    public ReviewMapper(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    public ReviewResponse toResponse(Review review) {
        ReviewResponse dto = new ReviewResponse();
        dto.setId(review.getId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setProductId(review.getProduct().getId());
        dto.setProductName(review.getProduct().getReference());
        dto.setUserId(review.getUser().getId());
        dto.setUserName(review.getUser().getFirstName() + " " + review.getUser().getLastName());
        dto.setStatus(review.getStatus());
        dto.setCreatedAt(review.getCreatedAt());
        dto.setDeletedAt(review.getDeletedAt());
        return dto;
    } 
    
    public Review toEntity(ReviewRequest request) {
        Review dto = new Review();
        dto.setRating(request.getRating());
        dto.setComment(request.getComment());
        dto.setStatus(ReviewStatus.PENDING);

        Optional<User> user = userService.getUserById(request.getUserId());
        if (user.isPresent()) {
            dto.setUser(user.get());
        }
        Optional<Product> product = productService.getProductById(request.getProductId());
        if (product.isPresent()) {
            dto.setProduct(product.get());
        }
        return dto;
    }

    public Review updateEntity(Review review, ReviewRequest request) {
        
        if (request.getRating() != 0) {
            review.setRating(request.getRating());
        }
        if (request.getComment() != null){
            review.setComment(request.getComment());
        }
        return review;
    }

}
