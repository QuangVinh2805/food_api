package org.example.food_api.services;


import org.example.food_api.models.Notification;
import org.example.food_api.models.Notification;
import org.example.food_api.models.User;
import org.example.food_api.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    public ResponseEntity<List<Notification>> listAllNotification(){
        List<Notification> listNotification= notificationRepository.findAll();
        if(listNotification.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Notification>>(listNotification, HttpStatus.OK);
    }
    public void save(Notification notification){
        notificationRepository.save(notification);
    }
    public ResponseEntity<Notification> updateNotification(Notification notification){
        Long notificationId = notificationRepository.findIdByNotificationId(notification.getId());
        if (notificationId == null){
            String message = "notification id not found";
            System.out.println(message + notificationId);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        notificationRepository.save(notification);
        return new ResponseEntity(notification, HttpStatus.OK);
    }


    public ResponseEntity<String> deleteNotification(Long id){
        Long notificationId = notificationRepository.findIdByNotificationId(id);
        if (notificationId == null){
            String message = "notification id not found";
            System.out.println(message + notificationId);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        notificationRepository.deleteById(notificationId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
