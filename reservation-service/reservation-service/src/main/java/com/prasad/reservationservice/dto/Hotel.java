package com.prasad.reservationservice.dto;

import lombok.Data;

import java.util.List;


@Data
public class Hotel
{
    private Long id;
    private String name;
    private String city;
    private Integer availableRoom;
    private Double price;


    private List<Amenity> amenities;
}
