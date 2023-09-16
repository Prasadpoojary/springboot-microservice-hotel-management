package com.prasad.reservationservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ReservationAlreadyExistException extends  RuntimeException
{
    public ReservationAlreadyExistException(String message)
    {
        super(message);
    }
}
