package com.gtelant.commerce_service.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gtelant.commerce_service.dtos.ReviewRequest;
import com.gtelant.commerce_service.dtos.ReviewResponse;
import com.gtelant.commerce_service.mappers.ReviewMapper;
import com.gtelant.commerce_service.models.Review;
import com.gtelant.commerce_service.services.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/reviews")
@CrossOrigin("*")
public class ReviewContorller {
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    public ReviewContorller(ReviewService reviewService, ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
    }
    
    @Operation(summary = "取得所有Reviews列表", description = "")
    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews().stream()
                .map(reviewMapper::toResponse)
                .toList()
        );
    }

    @Operation(summary = "建立新的Review", description = "")
    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(@RequestBody ReviewRequest request) {
        boolean isCreated = reviewService.createReview(request);
        if (isCreated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "更新指定的Review", description = "")
    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> updateReview(@PathVariable int id, @RequestBody ReviewRequest request) {
        Optional<Review> review = reviewService.getReviewById(id);
        if (review.isPresent()) {
            Review savedReview = reviewService.saveReview(reviewMapper.updateEntity(review.get(), request));
            return ResponseEntity.ok(reviewMapper.toResponse(savedReview));
        }
        return ResponseEntity.notFound().build();
    }

}
