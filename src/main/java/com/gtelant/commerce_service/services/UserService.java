package com.gtelant.commerce_service.services;

import com.gtelant.commerce_service.models.Segment;
import com.gtelant.commerce_service.models.User;
import com.gtelant.commerce_service.models.UserSegment;
import com.gtelant.commerce_service.repositories.SegmentRepository;
import com.gtelant.commerce_service.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gtelant.commerce_service.repositories.UserSegmentRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final SegmentRepository segmentRepository;
    private final UserSegmentRepository userSegmentRepository;

    @Autowired
    public UserService(UserRepository userRepository, SegmentRepository segmentRepository, UserSegmentRepository userSegmentRepository) {
        this.userRepository = userRepository;
        this.segmentRepository = segmentRepository;
        this.userSegmentRepository = userSegmentRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public Page<User> getAllUsers(String queryName, PageRequest pageRequest) {
        if (queryName != null && !queryName.isEmpty()) {
            return userRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(queryName, queryName, pageRequest);
        }
        return userRepository.findAll(pageRequest);
    }
    
    public Page<User> getAllUsers(String queryName, Boolean hasNewsletter, Integer segmentId, PageRequest pageRequest) {
        Specification<User> spec = userSpecification(queryName, hasNewsletter, segmentId);
        return userRepository.findAll(spec, pageRequest);
    }

    private Specification<User> userSpecification(String queryName, Boolean hasNewsletter, Integer segmentId) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (queryName != null && !queryName.isEmpty()) {
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + queryName.toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + queryName.toLowerCase() + "%")
                ));
            }

            if (hasNewsletter != null) {
                predicates.add(criteriaBuilder.equal(root.get("hasNewsletter"), hasNewsletter));
            }

            if (segmentId != null) {
                Join<User, UserSegment> userUserSegmentJoin = root.join("userSegment");
                predicates.add(criteriaBuilder.equal(userUserSegmentJoin.get("segment").get("id"), segmentId));
            }

            Predicate[] predicateArray = predicates.toArray(new Predicate[0]);
            return criteriaBuilder.and(predicateArray);
        });
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public boolean assignSegmentToUser(int id, int segmentId) {
        Optional<User> user = userRepository.findById(id);
        Optional<Segment> segment = segmentRepository.findById(segmentId);
        if (user.isPresent() && segment.isPresent()) {
            UserSegment userSegment = new UserSegment();
            userSegment.setSegment(segment.get());
            userSegment.setUser(user.get());
            userSegmentRepository.save(userSegment);
            return true;
        }
        return false;
    }
}
