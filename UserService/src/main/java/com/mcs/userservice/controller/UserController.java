package com.mcs.userservice.controller;

import com.mcs.userservice.entities.User;
import com.mcs.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<User> createUser(@RequestBody User user){
        String uuid= UUID.randomUUID().toString();
        user.setUserId(uuid);
        User user1= userService.addUser(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User>getUserById(@PathVariable  String userId) {
        User user1=userService.getUserById(userId);
        return ResponseEntity.ok(user1);
    }

    public ResponseEntity<List<User>> getAllUser(){
        List<User> users=userService.getAllUser();
        return ResponseEntity.ok(this.userService.getAllUser());
    }
}
