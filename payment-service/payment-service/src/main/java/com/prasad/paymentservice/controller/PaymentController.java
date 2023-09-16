package com.prasad.paymentservice.controller;


import com.prasad.paymentservice.dto.PaymentStatusUpdateDTO;
import com.prasad.paymentservice.exceptions.PaymentNotFoundException;
import com.prasad.paymentservice.model.Payment;
import com.prasad.paymentservice.proxy.PaymentServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController
{

    @Autowired
    private PaymentServiceProxy paymentService;


    @PostMapping("/initiate")
    ResponseEntity<Payment> pay(@RequestBody Payment payment)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.pay(payment));
    }

    @GetMapping("/{id}")
    ResponseEntity<Payment> getPayment(@PathVariable Long id) throws PaymentNotFoundException
    {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.getPayment(id));
    }

    @GetMapping("/reservation/{id}")
    ResponseEntity<Payment> getPaymentOfReservation(@PathVariable Long id) throws PaymentNotFoundException
    {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.getPaymentByReservation(id));
    }


    @GetMapping("/")
    ResponseEntity<List<Payment>> getAllPayment()
    {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.getAllPayment());
    }

    @PatchMapping("/")
    ResponseEntity<Payment> updatePayment(@RequestBody PaymentStatusUpdateDTO paymentStatusUpdateDTO) throws PaymentNotFoundException
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.updatePayment(paymentStatusUpdateDTO.getId(),paymentStatusUpdateDTO.getPaymentStatus()));
    }

}
