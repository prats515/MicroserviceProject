package com.mcs.userservice.service.impl;

import com.mcs.userservice.entities.Hotel;
import com.mcs.userservice.entities.Rating;
import com.mcs.userservice.entities.User;
import com.mcs.userservice.exception.ResourceNotFoundException;
import com.mcs.userservice.externalservice.HotelService;
import com.mcs.userservice.repositories.UserRepo;
import com.mcs.userservice.service.UserService;
import jdk.jfr.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

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
        System.out.println("USetID from path"+id);
         User user=userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User",id));
         System.out.println("USER: "+user.getName());
         System.out.println("USetID"+user.getUserId());
        Rating[] userRatings=restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        List<Rating> ratings=Arrays.stream(userRatings).toList();


        List<Rating> ratingList=ratings.stream().map(rating ->{
            Hotel h= hotelService.getHotel(rating.getHotelId());
            //ResponseEntity<Hotel> hotelEntity=restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
            //Hotel hotel= h.getBody();
            rating.setHotel(h);
            //System.out.println("Status code"+hotelEntity.getStatusCode());
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }

}
//.orElseThrow(()->new ResourceNotFoundException("User", "Id", userid));
