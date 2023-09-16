package com.prasad.paymentservice.dto;

import com.prasad.paymentservice.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStatusUpdateDTO
{
    private Long id;
    private PaymentStatus paymentStatus;
}
