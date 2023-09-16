package com.prasad.reservationservice.controller;



import com.prasad.reservationservice.exceptions.ReservationNotFoundException;
import com.prasad.reservationservice.model.Reservation;
import com.prasad.reservationservice.proxy.ReservationServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController
{

    @Autowired
    private ReservationServiceProxy reservationService;


    @PostMapping("/reserve")
    ResponseEntity<Reservation> reserve(@RequestBody Reservation reservation)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.reserve(reservation));
    }

    @GetMapping("/{id}")
    ResponseEntity<Reservation> getReservation(@PathVariable Long id) throws ReservationNotFoundException
    {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getReservation(id));
    }


    @GetMapping("/")
    ResponseEntity<List<Reservation>> getAllReservation()
    {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getAllReservation());
    }

    @PutMapping("/")
    ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation) throws ReservationNotFoundException
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.updateReservation(reservation));
    }

    @PatchMapping("/cancel/{id}")
    ResponseEntity<Reservation> cancelReservation(@PathVariable Long id) throws ReservationNotFoundException
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.cancelReservation(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteReservation(@PathVariable Long id) throws ReservationNotFoundException
    {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.deleteReservation(id));
    }



}
