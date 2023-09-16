package com.prasad.customerservice.dto;

import com.prasad.customerservice.enums.MessageStatus;

public class Message
{

    public String status;
    public String message;


    public Message(MessageStatus status, String message)
    {
        this.status = String.valueOf(status);
        this.message = message;
    }

    public Message()
    {
    }



}
