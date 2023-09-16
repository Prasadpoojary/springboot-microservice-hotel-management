package com.prasad.reservationservice.proxy;


import com.prasad.reservationservice.exceptions.ReservationNotFoundException;
import com.prasad.reservationservice.model.Reservation;
import java.util.List;

public interface IReservationService
{

    public Reservation reserve(Reservation reservation);

    public Reservation getReservation(Long id) throws ReservationNotFoundException;


    public List<Reservation> getAllReservation();


    public Reservation updateReservation(Reservation reservation) throws ReservationNotFoundException;

    public Reservation cancelReservation(Long id) throws ReservationNotFoundException;
    public boolean deleteReservation(Long id) throws ReservationNotFoundException;

}
