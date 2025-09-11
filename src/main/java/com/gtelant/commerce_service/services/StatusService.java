package com.gtelant.commerce_service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gtelant.commerce_service.models.Status;
import com.gtelant.commerce_service.repositories.StatusRepository;

@Service
public class StatusService {
    private final StatusRepository statusRepository;
    
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }
    
    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    public Optional<Status> getStatusById(int id) {
        return statusRepository.findById(id);
    }
}
