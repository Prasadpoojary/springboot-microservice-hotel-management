package com.prasad.notificationservice.proxy;

import com.prasad.notificationservice.exceptions.NotificationNotFoundException;
import com.prasad.notificationservice.model.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class NotificationServiceProxy implements INotificationService
{

    Logger logger= LoggerFactory.getLogger(NotificationServiceProxy.class);

    @Autowired
    private INotificationService notificationService;

    @Override
    public Notification notify(Notification notification)
    {
        logger.info("Entry into notify method");
        Notification newNotification= notificationService.notify(notification);
        logger.info("Exit from notify method");
        return newNotification;
    }

    @Override
    public Notification getNotification(Long id) throws NotificationNotFoundException {
        logger.info("Entry into getNotification method");
        Notification notification = notificationService.getNotification(id);
        logger.info("Exit from getNotification method");
        return notification;
    }

    @Override
    public List<Notification> getNotificationByUserId(Long id) throws NotificationNotFoundException {
        logger.info("Entry into getNotificationByUserId method");
        List<Notification> notifications = notificationService.getNotificationByUserId(id);
        logger.info("Exit from getNotificationByUserId method");
        return notifications;
    }

    @Override
    public List<Notification> getAllNotification() {
        logger.info("Entry into getAllNotification method");
        List<Notification> allPayment = notificationService.getAllNotification();
        logger.info("Exit from getAllNotification method");
        return allPayment;
    }

    @Override
    public Notification updateNotification(Notification notification) throws NotificationNotFoundException
    {
        logger.info("Entry into updateNotification method");
        Notification updatedNotification = notificationService.updateNotification(notification);
        logger.info("Exit from updateNotification method");
        return updatedNotification;
    }

    @Override
    public boolean deleteNotification(Long id) throws NotificationNotFoundException
    {
        logger.info("Entry into deleteNotification method");
        boolean status = notificationService.deleteNotification(id);
        logger.info("Exit from deleteNotification method");
        return status;
    }

}
