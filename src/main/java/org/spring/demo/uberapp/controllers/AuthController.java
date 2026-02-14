package org.spring.demo.uberapp.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.spring.demo.uberapp.dto.*;
import org.spring.demo.uberapp.servies.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

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

    @PostMapping("/login")
    ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto,
                                           HttpServletRequest servletRequest,
                                           HttpServletResponse httpServletResponse){
        String tokens[] = authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        Cookie cookie = new Cookie("token",tokens[1]);
        cookie.setHttpOnly(true);

        httpServletResponse.addCookie(cookie);

        return ResponseEntity.ok(
                LoginResponseDto.builder()
                        .accessToken(tokens[0])
                        .refreshToken(tokens[1])
                        .build()
        );
    }

}
