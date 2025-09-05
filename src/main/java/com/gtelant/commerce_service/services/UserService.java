package com.gtelant.commerce_service.services;

import com.gtelant.commerce_service.models.Segment;
import com.gtelant.commerce_service.models.User;
import com.gtelant.commerce_service.models.UserSegment;
import com.gtelant.commerce_service.repositories.SegmentRepository;
import com.gtelant.commerce_service.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

import com.gtelant.commerce_service.repositories.UserSegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    
    public Page<User> getAllUsers(String query, PageRequest pageRequest) {
        if (query != null && !query.isEmpty()) {
            return userRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(query, query, pageRequest);
        }
        return userRepository.findAll(pageRequest);
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
