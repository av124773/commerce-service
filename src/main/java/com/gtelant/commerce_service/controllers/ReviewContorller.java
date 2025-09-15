package com.gtelant.commerce_service.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gtelant.commerce_service.dtos.ReviewStatusRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Operation(summary = "取得所有Reviews列表(page)", 
            description = """                    
                    參數說明：
                    - page: 當前想要輸出第幾頁的資料。
                    - size: 每一頁最多幾筆資料。
                    - userId: 篩選特定user的評論。
                    - productId: 篩選特定product相關的評論。
                    """)
    @GetMapping("/page")
    public Page<ReviewResponse> getAllReviewsPage(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) Integer userId,
        @RequestParam(required = false) Integer productId
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return reviewService.getAllReviews(userId, productId, pageRequest).map(reviewMapper::toResponse);
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

    @Operation(summary = "更新指定Review的status", description = "")
    @PutMapping("/status")
    public ResponseEntity<ReviewResponse> updateStatus(@RequestBody ReviewStatusRequest request) {
        List<Review> reviews = reviewService.getReviewByIds(request.getIds());
        if (reviews.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Review> updatedReviews = new ArrayList<>();
        for (Review review : reviews) {
            review.setStatus(request.getReviewStatus());
            updatedReviews.add(review);
        }
        reviewService.saveAllReviews(updatedReviews);
        return ResponseEntity.ok(null);
        
    }

    @Operation(summary = "刪除指定的Review", description = "")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable int id) {
        Optional<Review> review = reviewService.getReviewById(id);
        if (review.isPresent()) {
            reviewService.deleteReview(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
