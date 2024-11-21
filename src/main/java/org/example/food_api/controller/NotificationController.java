package org.example.food_api.controller;

import org.example.food_api.models.Notification;
import org.example.food_api.services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    public static Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Notification>> listAllNotification(){
        return notificationService.listAllNotification();
    }
}
