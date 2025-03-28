package com.example.java_training.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping
    public List<String> getAllUsers() {
        return Arrays.asList("321321");
    }
}
