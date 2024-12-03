package org.example.food_api.controller;

import org.example.food_api.models.Notification;
import org.example.food_api.models.Notification;
import org.example.food_api.models.Notification;
import org.example.food_api.repository.NotificationRepository;
import org.example.food_api.services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    public static Logger logger = LoggerFactory.getLogger(NotificationController.class);
    @Autowired
    private NotificationRepository notificationRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Notification>> listAllNotification(){
        return notificationService.listAllNotification();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNotification(@PathVariable("id") long id) {
        return notificationService.deleteNotification(id);
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllNotification() {
        try {
            notificationRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<Notification> updateNotification(@RequestBody Notification notification) {
        return notificationService.updateNotification(notification);
    }
    @PostMapping("/create")
    public void createNotification(@RequestBody Notification notification) {
        notificationService.save(notification);
    }
}
