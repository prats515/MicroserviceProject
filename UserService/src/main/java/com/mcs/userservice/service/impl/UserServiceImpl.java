package com.mcs.userservice.service.impl;

import com.mcs.userservice.entities.User;
import com.mcs.userservice.exception.ResourceNotFoundException;
import com.mcs.userservice.repositories.UserRepo;
import com.mcs.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User addUser(User user) {
        return  userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(String id)  {
        return userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User",id));
    }
}
//.orElseThrow(()->new ResourceNotFoundException("User", "Id", userid));
