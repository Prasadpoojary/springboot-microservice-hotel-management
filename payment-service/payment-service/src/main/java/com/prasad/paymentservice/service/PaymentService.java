package com.prasad.paymentservice.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prasad.paymentservice.enums.PaymentStatus;
import com.prasad.paymentservice.enums.PaymentType;
import com.prasad.paymentservice.exceptions.PaymentNotFoundException;
import com.prasad.paymentservice.model.Payment;
import com.prasad.paymentservice.proxy.IPaymentService;
import com.prasad.paymentservice.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PaymentService implements IPaymentService
{
    @Autowired
    private PaymentRepository paymentRepository;


    @Autowired
    private KafkaTemplate<String,Object> template;

    Logger logger= LoggerFactory.getLogger(PaymentService.class);

    public Payment pay(Payment payment)
    {
            logger.info("Calling Payment gateway to process {} for customer",payment.getPaymentType());
            // logic to call payment gateway goes here
            Payment newPayment= paymentRepository.save(payment);
            logger.info("Payment is being processed");
            return newPayment;
    }

    public Payment getPayment(Long id) throws PaymentNotFoundException
    {
        return paymentRepository.findById(id).stream().findAny().orElseThrow(PaymentNotFoundException::new);
    }

    public Payment getPaymentByReservation(Long id) throws PaymentNotFoundException
    {
        return paymentRepository.findByReservationId(id).stream().findAny().orElseThrow(PaymentNotFoundException::new);
    }


    public List<Payment> getAllPayment()
    {
        return paymentRepository.findAll();
    }


    public Payment updatePayment(Long id, PaymentStatus status) throws PaymentNotFoundException
    {
        if(paymentRepository.existsById(id))
        {
            try
            {
                Payment payment=paymentRepository.findById(id).get();
                payment.setStatus(status);
                ObjectMapper mapper=new ObjectMapper();
                CompletableFuture<SendResult<String,Object>> response= template.send("HotelPaymentTopic",mapper.writeValueAsString(payment));
                response.whenComplete((result,ex)->{
                    if (ex == null) {
                        logger.info("Sent message with offset=[" + result.getRecordMetadata().offset() + "]");
                    } else {
                        logger.info("Unable to send message due to : {}" , ex.getMessage());
                    }
                });

                return paymentRepository.save(payment);
            }
            catch(Exception e)
            {
                logger.error("Unable to update the payment status : {}",e);
                throw new RuntimeException(e.getMessage());
            }
        }
        else
        {
            PaymentNotFoundException payment_not_found = new PaymentNotFoundException("Payment not found");
            logger.error("Business exception occurred : {}",payment_not_found);
            throw payment_not_found;
        }

    }






}
