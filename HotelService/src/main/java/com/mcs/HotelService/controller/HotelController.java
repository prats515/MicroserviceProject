package com.mcs.HotelService.controller;

import com.mcs.HotelService.entities.Hotel;
import com.mcs.HotelService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/hotel")
@RestController
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping("/create")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return  ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Hotel>>getHotels(){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAll());
    }
}
