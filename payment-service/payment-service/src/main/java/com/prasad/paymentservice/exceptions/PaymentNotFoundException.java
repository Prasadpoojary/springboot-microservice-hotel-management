package com.prasad.paymentservice.exceptions;

public class PaymentNotFoundException extends  RuntimeException
{
    public PaymentNotFoundException()
    {

    }
    public PaymentNotFoundException(String message)
    {
        super(message);
    }
}
