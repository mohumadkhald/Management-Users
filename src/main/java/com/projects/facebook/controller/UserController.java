package com.projects.facebook.controller;

import com.projects.facebook.model.User;
import com.projects.facebook.service.UserService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User storeUser(@Valid @RequestBody User user)
    {
        return userService.storeUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> showUser(@PathVariable  Integer id)
    {
        User user = userService.showUser(id);
        return  ResponseEntity.ok(user);
    }

}
