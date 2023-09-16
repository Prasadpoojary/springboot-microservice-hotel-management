package com.prasad.customerservice.exceptions;

public class AccountNotFoundException extends RuntimeException
{
    AccountNotFoundException(String message)
    {
        super(message);
    }
}
