package com.mcs.userservice.service.impl;

import com.mcs.userservice.entities.Rating;
import com.mcs.userservice.entities.User;
import com.mcs.userservice.exception.ResourceNotFoundException;
import com.mcs.userservice.repositories.UserRepo;
import com.mcs.userservice.service.UserService;
import jdk.jfr.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

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
         User user=userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User",id));
        logger.info("before mcs: ");
         //http://localhost:1111/ratings/users/a4ae54d7-8704-4e6b-bd3c-192465ca49b0
        ArrayList<Rating> userRatings=restTemplate.getForObject("http://localhost:1111/ratings/users/"+user.getUserId(), ArrayList.class);
        user.setRatings(userRatings);
        System.out.println("AL: "+userRatings);
        return user;
    }

}
//.orElseThrow(()->new ResourceNotFoundException("User", "Id", userid));
