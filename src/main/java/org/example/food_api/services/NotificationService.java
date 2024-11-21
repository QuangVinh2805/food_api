package org.example.food_api.services;


import org.example.food_api.models.Notification;
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
}
