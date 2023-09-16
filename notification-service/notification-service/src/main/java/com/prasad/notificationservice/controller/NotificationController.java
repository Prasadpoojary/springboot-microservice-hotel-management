package com.prasad.notificationservice.controller;


import com.prasad.notificationservice.exceptions.NotificationNotFoundException;
import com.prasad.notificationservice.model.Notification;
import com.prasad.notificationservice.proxy.NotificationServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController
{

    @Autowired
    private NotificationServiceProxy notificationService;


    @PostMapping("/notify")
    ResponseEntity<Notification> notify(@RequestBody Notification notification)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(notificationService.notify(notification));
    }

    @GetMapping("/{id}")
    ResponseEntity<Notification> getNotification(@PathVariable Long id) throws NotificationNotFoundException
    {
        return ResponseEntity.status(HttpStatus.OK).body(notificationService.getNotification(id));
    }

    @GetMapping("/customer/{id}")
    ResponseEntity<List<Notification>> getNotificationByCustomer(@PathVariable Long id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(notificationService.getNotificationByUserId(id));
    }

    @GetMapping("/")
    ResponseEntity<List<Notification>> getAllNotification()
    {
        return ResponseEntity.status(HttpStatus.OK).body(notificationService.getAllNotification());
    }

    @PutMapping("/")
    ResponseEntity<Notification> updateNotification(@RequestBody Notification notification) throws NotificationNotFoundException
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(notificationService.updateNotification(notification));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteNotification(@PathVariable Long id) throws NotificationNotFoundException
    {
        return ResponseEntity.status(HttpStatus.OK).body(notificationService.deleteNotification(id));
    }


}
