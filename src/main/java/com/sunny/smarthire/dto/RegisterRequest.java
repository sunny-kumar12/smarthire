package com.sunny.smarthire.dto;

import com.sunny.smarthire.entity.User;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private User.Role role;
}