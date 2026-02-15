package org.spring.demo.uberapp.controllers;


import lombok.RequiredArgsConstructor;
import org.spring.demo.uberapp.dto.*;
import org.spring.demo.uberapp.servies.DriverService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/drivers",produces = MediaType.APPLICATION_JSON_VALUE)
@Secured("ROLE_DRIVER")
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

    @PostMapping("/cancelRide/{rideId}")
    private ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId){
        return new ResponseEntity<>(driverService.cancelRide(rideId), HttpStatus.OK);
    }

    @PostMapping("/rateDriver")
    private ResponseEntity<RiderDto> rateDriver(@RequestBody RatingDto ratingDto){
        return new ResponseEntity<>(driverService.rateRider(ratingDto.getRideId(),ratingDto.getRating()), HttpStatus.OK);
    }

    @GetMapping("/getMyProfile")
    private ResponseEntity<DriverDto> getMyProfile(){
        return new ResponseEntity<>(driverService.getMyProfile(), HttpStatus.OK);
    }

    @GetMapping("/getMyRides")
    private ResponseEntity<Page<RideDto>> getAllMyRides(@RequestParam(defaultValue = "0") Integer pageOffset,
                                                        @RequestParam(defaultValue = "10",required = false) Integer pageSize){
        PageRequest pageRequest = PageRequest
                .of(pageOffset,pageSize, Sort.by(Sort.Direction.DESC,"createdTime","id"));
        return new ResponseEntity<>(driverService.getAllMyRides(pageRequest), HttpStatus.OK);
    }

    @PostMapping("/rateRider/{rideId}/{rating}")
    private ResponseEntity<RiderDto> rateRider(@PathVariable Long rideId,@PathVariable Integer rating){
        return new ResponseEntity<>(driverService.rateRider(rideId,rating), HttpStatus.OK);
    }
}
