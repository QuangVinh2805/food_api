package org.example.food_api.repository;

import org.example.food_api.models.Notification;
import org.example.food_api.models.Cart;
import org.example.food_api.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    @Override
    List<Notification> findAll();
    Notification findById(long id);

    @Query(value = "select n.id from Notification n where n.id = :id",nativeQuery = false)
    Long findIdByNotificationId(@Param("id") Long id);
}
