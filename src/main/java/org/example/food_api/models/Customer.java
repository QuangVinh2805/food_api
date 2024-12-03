package org.example.food_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 200)
    @Column(name = "avatar", length = 200)
    private String avatar;

    @Size(max = 200)
    @Column(name = "gender", length = 200)
    private String gender;

    @Size(max = 200)
    @Column(name = "password", length = 200)
    private String password;

    @Column(name = "birthday")
    private Instant birthday;

    @Size(max = 100)
    @Column(name = "customer_name", length = 100)
    private String customerName;

    @Size(max = 100)
    @Column(name = "address", length = 100)
    private String address;

    @Size(max = 100)
    @Column(name = "phone", length = 100)
    private String phone;

    @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;

    @Size(max = 255)
    @Column(name = "token")
    private String token;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "status", nullable = false)
    private Byte status;

    public Customer() {}

    public Customer(String avatar,String gender,String password,Instant birthday,String customerName,String address,String phone,String email) {
        this.avatar = avatar;
        this.gender = gender;
        this.password = password;
        this.birthday = birthday;
        this.customerName = customerName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

}