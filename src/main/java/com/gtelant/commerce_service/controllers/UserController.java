package com.gtelant.commerce_service.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gtelant.commerce_service.dtos.UserRequest;
import com.gtelant.commerce_service.dtos.UserResponse;
import com.gtelant.commerce_service.mappers.UserMapper;
import com.gtelant.commerce_service.models.User;
import com.gtelant.commerce_service.services.UserService;

import io.swagger.v3.oas.annotations.Operation;

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

    @Operation(summary = "取得指定使用者資料", description = "")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable int id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(userMapper.toResponse(user.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "新增使用者", description = "")
    @PostMapping()
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        User user = userMapper.toEntity(request);
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(userMapper.toResponse(savedUser));
    }

    @Operation(summary = "更新指定使用者資料", 
            description = """
                    請求範例：
                    ```json
                    {
                        "firstName": null,
                        "lastName": null,
                        "email": null,
                        "birthday": null,
                        "address": "new address",
                        "city": null,
                        "state": null,
                        "zipcode": null,
                        "role": null,
                        "password": null,
                        "hasNewsletter": null
                    }
                    ```
                    
                    說明：
                    - null 表示該欄位不更新
                    - 有值則更新為新的值
                    - hasNewsletter 為 boolean 型別，預設為 false
                    """
    )
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable int id, @RequestBody UserRequest request) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            User updatedUser = userMapper.updateEntity(user.get(), request);
            User savedUser = userService.saveUser(updatedUser);
            return ResponseEntity.ok(userMapper.toResponse(savedUser));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // @PostMapping("/{id}/segments/{segmentId}")
    // public ResponseEntity<Void> assignSegmentToUser(@PathVariable int id, @PathVariable int segmentId) {
    //     // todo
    //     return ResponseEntity.ok().build();
    // }
}
