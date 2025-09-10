package com.gtelant.commerce_service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gtelant.commerce_service.dtos.ReviewRequest;
import com.gtelant.commerce_service.mappers.ReviewMapper;
import com.gtelant.commerce_service.models.Product;
import com.gtelant.commerce_service.models.Review;
import com.gtelant.commerce_service.models.User;
import com.gtelant.commerce_service.repositories.ReviewRepository;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final ProductService productService;
    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewRepository reviewRepository, UserService userService, ProductService productService, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.productService = productService;
        this.reviewMapper = reviewMapper;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(int id) {
        return reviewRepository.findById(id);
    }

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public void deleteReview(int id) {
        reviewRepository.deleteById(id);
    }

    public boolean createReview(ReviewRequest request) {
        Optional<User> user = userService.getUserById(request.getUserId());
        Optional<Product> product = productService.getProductById(request.getProductId());
        if (user.isPresent() && product.isPresent()) {
            reviewRepository.save(reviewMapper.toEntity(request));
            return true;
        }
        return false;
    }
}
