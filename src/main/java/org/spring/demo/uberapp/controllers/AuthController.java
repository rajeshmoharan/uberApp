package org.spring.demo.uberapp.controllers;

import lombok.RequiredArgsConstructor;
import org.spring.demo.uberapp.dto.DriverDto;
import org.spring.demo.uberapp.dto.SignUpDto;
import org.spring.demo.uberapp.dto.UserDto;
import org.spring.demo.uberapp.servies.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto){
        return new ResponseEntity<>(authService.signUp(signUpDto), HttpStatus.CREATED);
    }

    @PostMapping("/onBoardNewDriver/{userId}/{vechileId}")
    ResponseEntity<DriverDto> onBoardNewDriver(@PathVariable Long userId,@PathVariable String vechileId){
        return new ResponseEntity<>(authService.onboardingNewDriver(userId,vechileId), HttpStatus.CREATED);
    }

}
