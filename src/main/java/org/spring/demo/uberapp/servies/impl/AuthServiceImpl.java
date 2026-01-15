package org.spring.demo.uberapp.servies.impl;

import org.spring.demo.uberapp.dto.DriverDto;
import org.spring.demo.uberapp.dto.SignUpDto;
import org.spring.demo.uberapp.dto.UserDto;
import org.spring.demo.uberapp.servies.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public UserDto signUp(SignUpDto signUpDto) {
        return null;
    }

    @Override
    public DriverDto onboardingNewDriver(Long userId) {
        return null;
    }
}
