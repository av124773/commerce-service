package com.gtelant.commerce_service.controllers;

import com.gtelant.commerce_service.models.User;
import com.gtelant.commerce_service.repositories.UserRepository;
import com.gtelant.commerce_service.responses.GetUserResponse;
import com.gtelant.commerce_service.responses.UpdateUserResponse;

import io.swagger.v3.oas.annotations.Operation;

import com.gtelant.commerce_service.requests.CreateUserRequest;
import com.gtelant.commerce_service.requests.UpdateUserRequest;

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
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Operation(summary = "取得所有使用者列表", 
            description = "")
    @GetMapping
    public ResponseEntity<List<GetUserResponse>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users.stream().map(GetUserResponse::new).toList());
    }

//    @Operation(summary = "取得所有使用者列表(分頁)",
//            description = "")
//    @GetMapping("/page")
//    public Page<GetUserResponse> getAllUsersPage(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ) {
//        PageRequest pageRequest = PageRequest.of(page, size);
//        Page<User> users = userRepository.findAll(pageRequest);
//        return ;
//    }

    @Operation(summary = "取得指定使用者資料", 
            description = "")
    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> getUserById(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            GetUserResponse response = new GetUserResponse(user.get());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "新增使用者", 
            description = "")
    @PostMapping()
    public ResponseEntity<GetUserResponse> createUser(@RequestBody CreateUserRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setBirthday(java.time.LocalDate.parse(request.getBirthday()));
        user.setAddress(request.getAddress());
        user.setCity(request.getCity());
        user.setState(request.getState());
        user.setZipcode(request.getZipcode());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setHasNewsletter(request.isHasNewsletter());
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(new GetUserResponse(savedUser));

    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable int id, @RequestBody UpdateUserRequest request) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User updatedUser = user.get();
            updatedUser.setFirstName(request.getFirstName());
            updatedUser.setLastName(request.getLastName());
            updatedUser.setBirthday(java.time.LocalDate.parse(request.getBirthday()));
            updatedUser.setAddress(request.getAddress());
            updatedUser.setCity(request.getCity());
            updatedUser.setState(request.getState());
            updatedUser.setZipcode(request.getZipcode());
            updatedUser.setHasNewsletter(request.isHasNewsletter());

            User savedUser = userRepository.save(updatedUser);
            UpdateUserResponse response = new UpdateUserResponse(savedUser);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/segments/{segmentId}")
    public ResponseEntity<Void> assignSegmentToUser(@PathVariable int id, @PathVariable int segmentId) {
        // todo
        return ResponseEntity.ok().build();
    }
}
