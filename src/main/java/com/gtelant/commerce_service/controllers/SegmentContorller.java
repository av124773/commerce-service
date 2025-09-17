package com.gtelant.commerce_service.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gtelant.commerce_service.dtos.SegmentRequest;
import com.gtelant.commerce_service.dtos.SegmentResponse;
import com.gtelant.commerce_service.mappers.SegmentMapper;
import com.gtelant.commerce_service.models.Segment;
import com.gtelant.commerce_service.services.SegmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/segments")
@CrossOrigin("*")
@SecurityRequirement(name = "bearerAuth")
public class SegmentContorller {
    private final SegmentService segmentService;
    private final SegmentMapper segmentMapper;

    public SegmentContorller(SegmentService segmentService, SegmentMapper segmentMapper) {
        this.segmentService = segmentService;
        this.segmentMapper = segmentMapper;
    }

    @Operation(summary = "取得所有Segment列表", description = "")
    @GetMapping
    public ResponseEntity<List<SegmentResponse>> getAllSegments() {
        List<Segment> segments = segmentService.getAllSegments();
        List<SegmentResponse> response = segments.stream()
                .map(segmentMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "新增Segment", description = "")
    @PostMapping()
    public ResponseEntity<SegmentResponse> createSegment(@RequestBody SegmentRequest request) {
        Segment segment = segmentMapper.toEntity(request);
        Segment savedSegment = segmentService.saveSegment(segment);
        return ResponseEntity.ok(segmentMapper.toResponse(savedSegment));
    }

    @Operation(summary = "修改Segment", description = "")
    @PutMapping("/{id}")
    public ResponseEntity<SegmentResponse> updateSegment(@PathVariable int id, @RequestBody SegmentRequest request) {
        Optional<Segment> segment = segmentService.getSegmentById(id);
        if (segment.isPresent()) {
            Segment updatedSegment = segmentMapper.updateEntity(segment.get(), request);
            Segment savedSegment = segmentService.saveSegment(updatedSegment);
            return ResponseEntity.ok(segmentMapper.toResponse(savedSegment));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "刪除指定Segment", description = "")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSegment(@PathVariable int id) {
        Optional<Segment> segment = segmentService.getSegmentById(id);
        if (segment.isPresent()) {
            segmentService.deleteSegment(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
