package com.gtelant.commerce_service.controllers;

import com.gtelant.commerce_service.dtos.UserRequest;
import com.gtelant.commerce_service.dtos.UserResponse;
import com.gtelant.commerce_service.mappers.UserMapper;
import com.gtelant.commerce_service.models.User;
import com.gtelant.commerce_service.services.UserService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Operation(summary = "取得所有使用者列表", description = "")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserResponse> response = users.stream()
                .map(userMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "取得所有使用者列表(分頁)", description = "")
    @GetMapping("/page")
    public Page<UserResponse> getAllUsersPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<User> users = userService.getAllUsers(pageRequest);
        Page<UserResponse> response = users.map(userMapper::toResponse);
        return ResponseEntity.ok(response).getBody();
    }

    @Operation(summary = "取得指定使用者資料", 
            description = "")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable int id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(userMapper.toResponse(user.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "新增使用者", 
            description = "")
    @PostMapping()
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        User user = userMapper.toEntity(request);
        User savedUser = userService.createUser(user);
        return ResponseEntity.ok(userMapper.toResponse(savedUser));
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable int id, @RequestBody UpdateUserRequest request) {
    //     Optional<User> user = userRepository.findById(id);
    //     if (user.isPresent()) {
    //         User updatedUser = user.get();
    //         updatedUser.setFirstName(request.getFirstName());
    //         updatedUser.setLastName(request.getLastName());
    //         updatedUser.setBirthday(java.time.LocalDate.parse(request.getBirthday()));
    //         updatedUser.setAddress(request.getAddress());
    //         updatedUser.setCity(request.getCity());
    //         updatedUser.setState(request.getState());
    //         updatedUser.setZipcode(request.getZipcode());
    //         updatedUser.setHasNewsletter(request.isHasNewsletter());

    //         User savedUser = userRepository.save(updatedUser);
    //         UpdateUserResponse response = new UpdateUserResponse(savedUser);
    //         return ResponseEntity.ok(response);
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    // @PostMapping("/{id}/segments/{segmentId}")
    // public ResponseEntity<Void> assignSegmentToUser(@PathVariable int id, @PathVariable int segmentId) {
    //     // todo
    //     return ResponseEntity.ok().build();
    // }
}
