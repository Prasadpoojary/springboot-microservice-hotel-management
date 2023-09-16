package com.prasad.paymentservice.model;

import com.prasad.paymentservice.enums.PaymentStatus;
import com.prasad.paymentservice.enums.PaymentType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Payment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long reservationId;
    private PaymentStatus status;
    private PaymentType paymentType;
    private Double amount;

}
