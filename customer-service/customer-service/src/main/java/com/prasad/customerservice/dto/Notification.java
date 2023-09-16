package com.prasad.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification
{
    private Long id;
    private Long userId;
    private String message;
    private Date timestamp;

}
