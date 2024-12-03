package org.example.food_api.repository;

import org.example.food_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);
    User findByUsername (String username);
    @Query(value = "select u.id from User u where u.id = :id",nativeQuery = false)
    Long findIdByUserId(@Param("id") Long id);

    @Query(value = "select u from User u where u.id = :id",nativeQuery = false)
    User findByUserId(Long id);
}
