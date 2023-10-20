package com.mcs.ratingservice.service.impl;

import com.mcs.ratingservice.entities.Rating;
import com.mcs.ratingservice.repo.RatingRepo;
import com.mcs.ratingservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepo ratingRepo;


    @Override
    public Rating createRating(Rating rating) {
        String s= UUID.randomUUID().toString();
        rating.setRatingId(s);
        return  ratingRepo.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return  ratingRepo.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepo.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepo.findByHotelId(hotelId);
    }
}
