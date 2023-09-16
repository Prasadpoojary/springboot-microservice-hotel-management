package com.prasad.reservationservice.dto;


import com.prasad.reservationservice.enums.PaymentStatus;
import com.prasad.reservationservice.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment
{
    private Long id;
    private Long reservationId;
    private PaymentStatus status;
    private PaymentType paymentType;
    private Double amount;

}
