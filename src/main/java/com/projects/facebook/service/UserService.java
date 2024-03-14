package com.projects.facebook.service;

import com.projects.facebook.model.User;

import java.util.List;

public interface UserService {
    User storeUser(User user);

    List<User> getAllUsers();

    User showUser(Integer id);
}
