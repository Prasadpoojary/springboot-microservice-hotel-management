package com.prasad.notificationservice.proxy;

import com.prasad.notificationservice.exceptions.NotificationNotFoundException;
import com.prasad.notificationservice.model.Notification;

import java.util.List;

public interface INotificationService
{

    public Notification notify(Notification notification);

    public Notification getNotification(Long id) throws NotificationNotFoundException;

    public List<Notification> getNotificationByUserId(Long id) throws NotificationNotFoundException;
    public List<Notification> getAllNotification();


    public Notification updateNotification(Notification notification) throws NotificationNotFoundException;


    public boolean deleteNotification(Long id) throws NotificationNotFoundException;


}
