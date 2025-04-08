package com.example.java_training.controller;

import com.example.java_training.dto.request.UserRequest;
import com.example.java_training.entity.UserEntity;
import com.example.java_training.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUser();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable long id) {
    return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<Object> createUser(
            @RequestBody UserRequest userRequest,
            @RequestParam(required = false) String role) {
        try {
            UserEntity newUserEntity = new UserEntity(userRequest);
            if ("admin".equalsIgnoreCase(role)) {
                newUserEntity.setRole("admin");
            }
            ResponseEntity<Object> response = userService.saveUser(newUserEntity);
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Error while creating user", e);
        }
    }

    @PostMapping("/update/{id}")
    public  ResponseEntity<Object> updateUser(@RequestBody UserRequest userRequest,@PathVariable Long id) {
        try {
            return  userService.updateUser(userRequest,id);
        }catch (Exception e) {
            throw new RuntimeException("Error while creating user", e);

        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteUser(@RequestParam Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID không được để trống");
            }

            return userService.deleteUser(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
