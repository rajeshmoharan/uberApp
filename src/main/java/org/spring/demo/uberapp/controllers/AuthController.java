package org.spring.demo.uberapp.controllers;

import lombok.RequiredArgsConstructor;
import org.spring.demo.uberapp.dto.SignUpDto;
import org.spring.demo.uberapp.dto.UserDto;
import org.spring.demo.uberapp.servies.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    UserDto signUp(@RequestBody SignUpDto signUpDto){
        return authService.signUp(signUpDto);
    }

}
