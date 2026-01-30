package org.spring.demo.uberapp.controllers;


import lombok.RequiredArgsConstructor;
import org.spring.demo.uberapp.dto.RideDto;
import org.spring.demo.uberapp.dto.RideStartDto;
import org.spring.demo.uberapp.servies.DriverService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/drivers",produces = MediaType.APPLICATION_JSON_VALUE)
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/acceptRide/{rideRequestId}")
    public ResponseEntity<RideDto> acceptRide(@PathVariable Long rideRequestId){
        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
    }

    @PostMapping("/startRide/{rideRequestId}")
    public ResponseEntity<RideDto> startRide(@PathVariable Long rideRequestId, @RequestBody RideStartDto rideStartDto){
        return ResponseEntity.ok(driverService.startRide(rideRequestId,rideStartDto.getOtp()));
    }

    @PostMapping("/endRide/{rideRequestId}")
    public ResponseEntity<RideDto> endRide(@PathVariable Long rideRequestId){
        return ResponseEntity.ok(driverService.endRide(rideRequestId));
    }
}
