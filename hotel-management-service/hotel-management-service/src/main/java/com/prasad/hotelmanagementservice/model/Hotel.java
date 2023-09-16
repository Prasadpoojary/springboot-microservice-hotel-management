package com.prasad.hotelmanagementservice.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Hotel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private Integer availableRoom;
    private Double price;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Amenity> amenities;
}
