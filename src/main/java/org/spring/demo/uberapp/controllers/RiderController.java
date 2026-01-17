package org.spring.demo.uberapp.controllers;

import lombok.RequiredArgsConstructor;
import org.spring.demo.uberapp.dto.RideRequestDto;
import org.spring.demo.uberapp.servies.RiderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rider")
@RequiredArgsConstructor
public class RiderController {

    private final RiderService riderService;

    @PostMapping("/requestRide")
    private ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto){
        return new ResponseEntity<>(riderService.requestRide(rideRequestDto), HttpStatus.OK);
    }

}
