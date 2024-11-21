package org.example.food_api.repository;

import org.example.food_api.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    @Override
    List<Notification> findAll();
}
