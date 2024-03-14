package com.projects.facebook.controller;

import com.projects.facebook.model.User;
import com.projects.facebook.service.UserService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(value = "http://localhost:3000/")
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

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Integer id)
    {
        boolean deleted = false;
        deleted = userService.deleteUser(id);
        Map<String, Boolean> res = new HashMap<>();
        res.put("deleted", deleted);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @Valid @RequestBody User user)
    {
        user = userService.updateUser(id, user);
        return ResponseEntity.ok(user);
    }
}
