package com.prasad.hotelmanagementservice.proxy;

import com.prasad.hotelmanagementservice.exceptions.HotelNotFoundException;
import com.prasad.hotelmanagementservice.model.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class HotelServiceProxy implements IHotelService
{

    Logger logger= LoggerFactory.getLogger(HotelServiceProxy.class);

    @Autowired
    private IHotelService hotelService;

    @Override
    public Hotel addHotel(Hotel hotel)
    {
        logger.info("Entry into addHotel method");
        Hotel newHotel= hotelService.addHotel(hotel);
        logger.info("Exit from addHotel method");
        return newHotel;
    }

    @Override
    public Hotel getHotel(Long id) throws HotelNotFoundException {
        logger.info("Entry into getHotel method");
        Hotel hotel = hotelService.getHotel(id);
        logger.info("Exit from getHotel method");
        return hotel;
    }

    @Override
    public List<Hotel> getAllHotel() {
        logger.info("Entry into getAllHotel method");
        List<Hotel> allHotel = hotelService.getAllHotel();
        logger.info("Exit from getAllHotel method");
        return allHotel;
    }

    @Override
    public Hotel updateHotel(Hotel hotel) throws HotelNotFoundException {
        logger.info("Entry into updateHotel method");
        Hotel updatedHotel = hotelService.updateHotel(hotel);
        logger.info("Exit from updateHotel method");
        return updatedHotel;
    }

    @Override
    public boolean deleteHotel(Long id) throws HotelNotFoundException {
        logger.info("Entry into deleteHotel method");
        boolean status = hotelService.deleteHotel(id);
        logger.info("Exit from deleteHotel method");
        return status;
    }
}
