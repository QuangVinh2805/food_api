package org.example.food_api.repository;

import org.example.food_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);
    User findByUsername (String username);
}
