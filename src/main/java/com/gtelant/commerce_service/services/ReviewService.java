package com.gtelant.commerce_service.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gtelant.commerce_service.dtos.ReviewRequest;
import com.gtelant.commerce_service.mappers.ReviewMapper;
import com.gtelant.commerce_service.models.Product;
import com.gtelant.commerce_service.models.Review;
import com.gtelant.commerce_service.models.User;
import com.gtelant.commerce_service.repositories.ReviewRepository;

import jakarta.persistence.criteria.Predicate;

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

    public Page<Review> getAllReviews(Integer userId, Integer productId, PageRequest pageRequest) {
        Specification<Review> spec = reviewSpecification(userId, productId);
        return reviewRepository.findAll(spec, pageRequest);
    }

    public Specification<Review> reviewSpecification(Integer userId, Integer productId) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (userId != null) {
                predicates.add(
                    criteriaBuilder.equal(root.get("user").get("id"), userId)
                );
            }

            if (productId != null) {
                predicates.add(
                    criteriaBuilder.equal(root.get("product").get("id"), productId)
                );
            }

            Predicate[] prediateArray = predicates.toArray(new Predicate[0]);
            return criteriaBuilder.and(prediateArray);
        });
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
