package org.example.food_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Builder
public class User {
    public static final int ROLE_ADMIN = 1;
    public static final int ROLE_USER = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 200)
    @Column(name = "username", length = 200)
    private String username;

    @Size(max = 200)
    @Column(name = "password", length = 200)
    private String password;

    @Column(name = "birthday")
    private Date birthday;

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

    @Size(max = 100)
    @Column(name = "verification_code", length = 100)
    private String verificationCode;

    @Column(name = "phone", length = 100)
    private Long phoneNumber;
}