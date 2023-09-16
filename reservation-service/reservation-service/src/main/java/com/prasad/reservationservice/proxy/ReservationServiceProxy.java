package com.prasad.reservationservice.proxy;

import com.prasad.reservationservice.exceptions.ReservationNotFoundException;
import com.prasad.reservationservice.model.Reservation;
import com.prasad.reservationservice.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ReservationServiceProxy implements IReservationService
{

    Logger logger= LoggerFactory.getLogger(ReservationServiceProxy.class);

    @Autowired
    private IReservationService reservationService;

    @Override
    public Reservation reserve(Reservation reservation)
    {
        logger.info("Entry into reserve method");
        Reservation newReservation= reservationService.reserve(reservation);
        logger.info("Exit from reserve method");
        return newReservation;
    }

    @Override
    public Reservation getReservation(Long id) throws ReservationNotFoundException {
        logger.info("Entry into getReservation method");
        Reservation reservation = reservationService.getReservation(id);
        logger.info("Exit from getReservation method");
        return reservation;
    }

    @Override
    public List<Reservation> getAllReservation() {
        logger.info("Entry into getAllReservation method");
        List<Reservation> allReservation = reservationService.getAllReservation();
        logger.info("Exit from getAllReservation method");
        return allReservation;
    }

    @Override
    public Reservation updateReservation(Reservation reservation) throws ReservationNotFoundException {
        logger.info("Entry into updateReservation method");
        Reservation updatedReservation = reservationService.updateReservation(reservation);
        logger.info("Exit from updateReservation method");
        return updatedReservation;
    }

    @Override
    public Reservation cancelReservation(Long id) throws ReservationNotFoundException
    {
        logger.info("Entry into cancelReservation method");
        Reservation reservation = reservationService.cancelReservation(id);
        logger.info("Exit from cancelReservation method");
        return reservation;
    }

    @Override
    public boolean deleteReservation(Long id) throws ReservationNotFoundException {
        logger.info("Entry into deleteReservation method");
        boolean status = reservationService.deleteReservation(id);
        logger.info("Exit from deleteReservation method");
        return status;
    }
}
