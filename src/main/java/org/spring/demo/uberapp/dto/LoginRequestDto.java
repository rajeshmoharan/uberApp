package org.spring.demo.uberapp.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
