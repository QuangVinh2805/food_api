package org.example.food_api.request;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
public class CustomerRequest {

    private String avatar;

    private String gender;

    private String password;

    private Instant birthday;

    private String customerName;

    private String address;

    private String phone;

    private String email;

}