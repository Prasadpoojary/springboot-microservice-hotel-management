package com.prasad.reservationservice.dto;

import lombok.Data;


@Data
public class Amenity
{
    private Long id;
    private String name;
    private String description;
    private String brand;
}
