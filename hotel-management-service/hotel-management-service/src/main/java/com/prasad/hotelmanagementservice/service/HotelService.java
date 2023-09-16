package com.prasad.hotelmanagementservice.service;


import com.prasad.hotelmanagementservice.exceptions.HotelNotFoundException;
import com.prasad.hotelmanagementservice.model.Hotel;
import com.prasad.hotelmanagementservice.proxy.IHotelService;
import com.prasad.hotelmanagementservice.repository.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class HotelService implements IHotelService
{
    @Autowired
    private HotelRepository hotelRepository;

    Logger logger= LoggerFactory.getLogger(HotelService.class);

    public Hotel addHotel(Hotel hotel)
    {
            Hotel newHotel= hotelRepository.save(hotel);
            logger.info("Hotel added successfully");
            return newHotel;

    }

    public Hotel getHotel(Long id) throws HotelNotFoundException
    {
        return hotelRepository.findById(id).stream().findAny().orElseThrow(HotelNotFoundException::new);
    }


    public List<Hotel> getAllHotel()
    {
        return hotelRepository.findAll();
    }


    public Hotel updateHotel(Hotel hotel) throws HotelNotFoundException
    {
        if(hotelRepository.existsById(hotel.getId()))
        {
            try
            {
                return hotelRepository.save(hotel);
            }
            catch(Exception e)
            {
                logger.error("Unable to update the hotel : {}",e);
                return null;
            }
        }
        else
        {
            HotelNotFoundException hotel_not_found = new HotelNotFoundException("hotel not found");
            logger.error("Business exception occurred : {}",hotel_not_found);
            throw hotel_not_found;
        }

    }


    public boolean deleteHotel(Long id) throws HotelNotFoundException
    {
        if(hotelRepository.existsById(id))
        {
            try
            {
                hotelRepository.deleteById(id);
                return true;
            }
            catch(Exception e)
            {
                logger.error("Unable to delete the hotel : {}",e);
                return false;
            }
        }
        else
        {
            HotelNotFoundException hotel_not_found = new HotelNotFoundException("hotel not found");
            logger.error("Business exception occurred : {}",hotel_not_found);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Hotel not found",hotel_not_found);
        }

    }
}
