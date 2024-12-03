package org.example.food_api.request;


import lombok.Data;

import java.util.Date;

@Data
public class UserRequest {
    private String username;
    private String password;
    private Date birthday;
    private String name;
    private String address;
    private String email;
    private Long phoneNumber;
    private Long roleId;
}
