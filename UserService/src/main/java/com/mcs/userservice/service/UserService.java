package com.mcs.userservice.service;

import com.mcs.userservice.entities.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    List<User> getAllUser();
    User getUserById(String id);
}
