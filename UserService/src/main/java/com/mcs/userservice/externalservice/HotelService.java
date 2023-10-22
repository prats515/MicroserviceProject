package com.mcs.userservice.externalservice;

import com.mcs.userservice.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="Hotel-Service")
public interface HotelService {
    @GetMapping("/hotel/{id}")
    Hotel getHotel(@PathVariable String id);
}
