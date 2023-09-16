package com.prasad.paymentservice.proxy;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.prasad.paymentservice.enums.PaymentStatus;
import com.prasad.paymentservice.exceptions.PaymentNotFoundException;
import com.prasad.paymentservice.model.Payment;

import java.util.List;

public interface IPaymentService
{

    public Payment pay(Payment payment);

    public Payment getPayment(Long id) throws PaymentNotFoundException;

    public Payment getPaymentByReservation(Long id) throws PaymentNotFoundException;

    public List<Payment> getAllPayment();


    public Payment updatePayment(Long id, PaymentStatus status) throws PaymentNotFoundException;


}
