package com.prasad.reservationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification
{
    private Long id;
    private Long userId;
    private String message;
    private Date timestamp;
}
