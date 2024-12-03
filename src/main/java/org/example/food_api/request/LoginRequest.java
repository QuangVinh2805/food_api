package org.example.food_api.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
