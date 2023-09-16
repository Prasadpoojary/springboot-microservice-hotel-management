package com.prasad.reservationservice.feign;

import com.prasad.reservationservice.dto.Hotel;
import com.prasad.reservationservice.exceptions.HotelNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value="hotel-management-service",url ="localhost:8081/hotel")
public interface HotelFeign
{
    @GetMapping("/{id}")
    ResponseEntity<Hotel> getHotel(@PathVariable Long id) throws HotelNotFoundException;
    @PutMapping("/")
    ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel) throws HotelNotFoundException;
}
