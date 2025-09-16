package com.gtelant.commerce_service.controllers;

import com.gtelant.commerce_service.dtos.AuthResponse;
import com.gtelant.commerce_service.dtos.LoginRequest;
import com.gtelant.commerce_service.dtos.UserRequest;
import com.gtelant.commerce_service.mappers.UserMapper;
import com.gtelant.commerce_service.models.User;
import com.gtelant.commerce_service.services.JwtAuthService;
import com.gtelant.commerce_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
@CrossOrigin("*")
public class JwtContorller {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtAuthService jwtAuthService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRequest request) {
        User user = userMapper.toEntity(request);
        String token = jwtAuthService.register(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        String token = jwtAuthService.login(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
