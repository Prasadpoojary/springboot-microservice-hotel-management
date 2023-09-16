package com.prasad.notificationservice.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prasad.notificationservice.exceptions.NotificationNotFoundException;
import com.prasad.notificationservice.model.Notification;
import com.prasad.notificationservice.proxy.INotificationService;
import com.prasad.notificationservice.repository.NotificationRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService implements INotificationService
{
    @Autowired
    private NotificationRepository notificationRepository;

    Logger logger= LoggerFactory.getLogger(NotificationService.class);


    public Notification notify(Notification notification)
    {
            Notification newNotification= notificationRepository.save(notification);
            logger.info("Notification saved : {}", newNotification);
            return newNotification;

    }

    public Notification getNotification(Long id) throws NotificationNotFoundException
    {
        return notificationRepository.findById(id).stream().findAny().orElseThrow(NotificationNotFoundException::new);
    }

    public List<Notification> getNotificationByUserId(Long id)
    {
        return notificationRepository.findByUserId(id);
    }


    public List<Notification> getAllNotification()
    {
        return notificationRepository.findAll();
    }


    public Notification updateNotification(Notification notification) throws NotificationNotFoundException
    {
        if(notificationRepository.existsById(notification.getId()))
        {
            try
            {
                return notificationRepository.save(notification);
            }
            catch(Exception e)
            {
                logger.error("Unable to update notification : {}",e);
                return null;
            }
        }
        else
        {
            NotificationNotFoundException notification_not_found = new NotificationNotFoundException("Notification not found");
            logger.error("Business exception occurred : {}",notification_not_found);
            throw notification_not_found;
        }

    }

    @Override
    public boolean deleteNotification(Long id) throws NotificationNotFoundException
    {
        if(notificationRepository.existsById(id))
        {
            try
            {
                notificationRepository.deleteById(id);
                return true;
            }
            catch(Exception e)
            {
                logger.error("Unable to delete notification : {}",e);
                return false;
            }
        }
        else
        {
            NotificationNotFoundException notification_not_found = new NotificationNotFoundException("Notification not found");
            logger.error("Business exception occurred : {}",notification_not_found);
            throw notification_not_found;
        }

    }


    @KafkaListener(topics = "HotelNotificationTopic",groupId = "ar-group")
    public void paymentStatusEvent(ConsumerRecord notificationKafkaMessage) throws JsonProcessingException
    {
        Notification notificationMessage = new ObjectMapper().readValue(notificationKafkaMessage.value().toString(), Notification.class);
        logger.info("Kafka Message consumed : " + notificationMessage);
        try
        {
//          adding notification to database
            notify(notificationMessage);
            logger.error("Notification written from Kafka to Database");
        }
        catch (Exception e)
        {
            logger.error("Unable to write notification from Kafka to Database");
        }

    }


}
