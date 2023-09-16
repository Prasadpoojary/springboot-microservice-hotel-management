package com.prasad.reservationservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ReservationNotFoundException extends  RuntimeException
{
    public ReservationNotFoundException()
    {

    }
    public ReservationNotFoundException(String message)
    {
        super(message);
    }
}
