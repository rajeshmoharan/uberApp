package org.spring.demo.uberapp.servies.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.spring.demo.uberapp.dto.DriverDto;
import org.spring.demo.uberapp.dto.SignUpDto;
import org.spring.demo.uberapp.dto.UserDto;
import org.spring.demo.uberapp.entities.User;
import org.spring.demo.uberapp.entities.enums.Role;
import org.spring.demo.uberapp.exceptions.RuntimeConflictException;
import org.spring.demo.uberapp.repositories.UserRepository;
import org.spring.demo.uberapp.servies.AuthService;
import org.spring.demo.uberapp.servies.RiderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserDto signUp(SignUpDto signUpDto) {

        userRepository.findByEmail(signUpDto.getEmail())
                .ifPresent((e) -> {
                   throw  new RuntimeConflictException("User Already Exit with the UserId :"+e.getEmail());
                });

        User map = modelMapper.map(signUpDto, User.class);
        map.setRoles(Set.of(Role.RIDER));
        User saved = userRepository.save(map);

        riderService.createNewRider(saved);

        //TODO add wallet related service here

        return modelMapper.map(saved,UserDto.class);
    }

    @Override
    public DriverDto onboardingNewDriver(Long userId) {
        return null;
    }
}
