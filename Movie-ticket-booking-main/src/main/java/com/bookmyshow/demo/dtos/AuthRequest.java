package com.bookmyshow.demo.dtos;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
