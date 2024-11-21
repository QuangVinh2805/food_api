package org.example.food_api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    public static final int ROLE_ADMIN = 1;
    public static final int ROLE_USER = 2;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 200)
    @Column(name = "username", length = 200)
    private String username;

    @Size(max = 200)
    @Column(name = "password", length = 200)
    private String password;

    @Column(name = "birthday")
    private Instant birthday;

    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    @Size(max = 100)
    @Column(name = "address", length = 100)
    private String address;

    @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "role_id", nullable = false)
    private Long roleId;


}