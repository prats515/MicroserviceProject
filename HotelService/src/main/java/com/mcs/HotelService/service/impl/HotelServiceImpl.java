package com.mcs.HotelService.service.impl;

import com.mcs.HotelService.entities.Hotel;
import com.mcs.HotelService.exception.ResourceNotFoundException;
import com.mcs.HotelService.repository.HotelRepo;
import com.mcs.HotelService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepo hotelRepo;

    @Override
    public Hotel create(Hotel hotel) {
        String  id= UUID.randomUUID().toString();
        hotel.setId(id);
        return  hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return  hotelRepo.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource not found with id "+id));
    }
}
