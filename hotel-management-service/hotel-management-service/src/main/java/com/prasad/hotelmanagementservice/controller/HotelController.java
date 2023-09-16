package com.prasad.hotelmanagementservice.controller;


import com.prasad.hotelmanagementservice.exceptions.HotelNotFoundException;
import com.prasad.hotelmanagementservice.model.Hotel;
import com.prasad.hotelmanagementservice.proxy.HotelServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController
{

    @Autowired
    private HotelServiceProxy hotelService;


    @PostMapping("/add")
    ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.addHotel(hotel));
    }

    @GetMapping("/{id}")
    ResponseEntity<Hotel> getHotel(@PathVariable Long id) throws HotelNotFoundException
    {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotel(id));
    }


    @GetMapping("/")
    ResponseEntity<List<Hotel>> getAllHotel()
    {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAllHotel());
    }

    @PutMapping("/")
    ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel) throws HotelNotFoundException
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.updateHotel(hotel));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteHotel(@PathVariable Long id) throws HotelNotFoundException
    {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.deleteHotel(id));
    }



}
