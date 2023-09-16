package com.prasad.hotelmanagementservice.proxy;



import com.prasad.hotelmanagementservice.exceptions.HotelNotFoundException;
import com.prasad.hotelmanagementservice.model.Hotel;

import java.util.List;

public interface IHotelService
{

    public Hotel addHotel(Hotel hotel);

    public Hotel getHotel(Long id) throws HotelNotFoundException;


    public List<Hotel> getAllHotel();


    public Hotel updateHotel(Hotel hotel) throws HotelNotFoundException;


    public boolean deleteHotel(Long id) throws HotelNotFoundException;

}
