package com.gtelant.commerce_service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gtelant.commerce_service.models.Segment;
import com.gtelant.commerce_service.repositories.SegmentRepository;

@Service
public class SegmentService {

    private final SegmentRepository segmentRepository;

    public SegmentService(SegmentRepository segmentRepository) {
        this.segmentRepository = segmentRepository;
    }

    public List<Segment> getAllSegments() {
        return segmentRepository.findAll();
    }

    public Optional<Segment> getSegmentById(int id) {
        return segmentRepository.findById(id);
    }

    public Segment saveSegment(Segment segment) {
        return segmentRepository.save(segment);
    }

    public void deleteSegment(int id) {
        segmentRepository.deleteById(id);
    }
    
}
