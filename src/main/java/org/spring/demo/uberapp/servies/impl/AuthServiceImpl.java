package org.spring.demo.uberapp.servies.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.spring.demo.uberapp.dto.DriverDto;
import org.spring.demo.uberapp.dto.SignUpDto;
import org.spring.demo.uberapp.dto.UserDto;
import org.spring.demo.uberapp.entities.Driver;
import org.spring.demo.uberapp.entities.User;
import org.spring.demo.uberapp.entities.enums.Role;
import org.spring.demo.uberapp.exceptions.ResourceNotFoundException;
import org.spring.demo.uberapp.exceptions.RuntimeConflictException;
import org.spring.demo.uberapp.repositories.UserRepository;
import org.spring.demo.uberapp.servies.AuthService;
import org.spring.demo.uberapp.servies.DriverService;
import org.spring.demo.uberapp.servies.RiderService;
import org.spring.demo.uberapp.servies.WalletService;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final WalletService walletService;
    private final DriverService driverService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String[] login(String email, String password) {
        String tokens[] = new String[2];
        // TODO : Implement the login logic and generate access and refresh tokens
        return tokens;
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
        map.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        User saved = userRepository.save(map);

        riderService.createNewRider(saved);

        walletService.createNewWallet(saved);
        return modelMapper.map(saved,UserDto.class);
    }

    @Override
    public DriverDto onboardingNewDriver(Long userId,String vechileId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with the user id" + userId));

        if(user.getRoles().contains(Role.DRIVER)){
            throw new RuntimeConflictException("User with the userid "+userId+" already a driver");
        }

        Driver driverBuilder = Driver.builder()
                .available(true)
                .rating(0.0)
                .user(user)
                .vechileId(vechileId)
                .build();
        user.getRoles().add(Role.DRIVER);
        userRepository.save(user);

        Driver savedDriver = driverService.createNewDriver(driverBuilder);
        return modelMapper.map(savedDriver, DriverDto.class);
    }
}
