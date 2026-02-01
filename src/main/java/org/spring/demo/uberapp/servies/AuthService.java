package org.spring.demo.uberapp.servies;

import org.spring.demo.uberapp.dto.DriverDto;
import org.spring.demo.uberapp.dto.SignUpDto;
import org.spring.demo.uberapp.dto.UserDto;

public interface AuthService {

    String login(String email,String password);

    UserDto signUp(SignUpDto signUpDto);

    DriverDto onboardingNewDriver(Long userId,String vechileId);
}
