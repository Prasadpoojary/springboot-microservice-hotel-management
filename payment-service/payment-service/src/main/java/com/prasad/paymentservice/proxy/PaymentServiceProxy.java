package com.prasad.paymentservice.proxy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.prasad.paymentservice.enums.PaymentStatus;
import com.prasad.paymentservice.exceptions.PayEligibilityException;
import com.prasad.paymentservice.exceptions.PaymentNotFoundException;
import com.prasad.paymentservice.model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PaymentServiceProxy implements IPaymentService
{

    Logger logger= LoggerFactory.getLogger(PaymentServiceProxy.class);

    @Autowired
    private IPaymentService paymentService;

    @Override
    public Payment pay(Payment payment)
    {
        logger.info("Entry into pay method of TYPE :"+ payment.getPaymentType());
        if(payment.getAmount()<=0)
        {
            throw new PayEligibilityException("Amount "+payment.getAmount()+" is not eligible for "+payment.getPaymentType());
        }
        Payment newPayment= paymentService.pay(payment);
        logger.info("Exit from pay method of TYPE :"+ payment.getPaymentType());
        return newPayment;
    }

    @Override
    public Payment getPayment(Long id) throws PaymentNotFoundException {
        logger.info("Entry into getPayment method");
        Payment payment = paymentService.getPayment(id);
        logger.info("Exit from getPayment method");
        return payment;
    }

    @Override
    public Payment getPaymentByReservation(Long id) throws PaymentNotFoundException
    {
        logger.info("Entry into getPaymentByReservation method");
        Payment payment = paymentService.getPaymentByReservation(id);
        logger.info("Exit from getPaymentByReservation method");
        return payment;
    }


    @Override
    public List<Payment> getAllPayment() {
        logger.info("Entry into getAllPayment method");
        List<Payment> allPayment = paymentService.getAllPayment();
        logger.info("Exit from getAllPayment method");
        return allPayment;
    }

    @Override
    public Payment updatePayment(Long id, PaymentStatus status) throws PaymentNotFoundException {
        logger.info("Entry into updatePayment method");
        Payment updatedPayment = paymentService.updatePayment(id,status);
        logger.info("Exit from updatePayment method");
        return updatedPayment;
    }



}
