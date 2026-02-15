package org.spring.demo.uberapp.controllers;

import lombok.RequiredArgsConstructor;
import org.spring.demo.uberapp.dto.*;
import org.spring.demo.uberapp.servies.RiderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rider")
@RequiredArgsConstructor
@Secured("ROLE_RIDER")
public class RiderController {

    private final RiderService riderService;

    @PostMapping("/requestRide")
    private ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto){
        return new ResponseEntity<>(riderService.requestRide(rideRequestDto), HttpStatus.OK);
    }

    @PostMapping("/cancelRide/{rideId}")
    private ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId){
        return new ResponseEntity<>(riderService.cancelRide(rideId), HttpStatus.OK);
    }

    @PostMapping("/rateDriver")
    private ResponseEntity<DriverDto> rateDriver(@RequestBody RatingDto ratingDto){
        return new ResponseEntity<>(riderService.rateDriver(ratingDto.getRideId(),ratingDto.getRating()), HttpStatus.OK);
    }

    @GetMapping("/getMyProfile")
    private ResponseEntity<RiderDto> getMyProfile(){
        return new ResponseEntity<>(riderService.getMyProfile(), HttpStatus.OK);
    }

    @GetMapping("/getMyRides")
    private ResponseEntity<Page<RideDto>> getAllMyRides(@RequestParam(defaultValue = "0") Integer pageOffset,
                                                        @RequestParam(defaultValue = "10",required = false) Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageOffset,pageSize, Sort.by(Sort.Direction.DESC,"createdTime","id"));
        return new ResponseEntity<>(riderService.getAllMyRides(pageRequest), HttpStatus.OK);
    }

    @PostMapping("/rateDriver/{rideId}/{rating}")
    private ResponseEntity<DriverDto> rateRider(@PathVariable Long rideId,@PathVariable Integer rating){
        return new ResponseEntity<>(riderService.rateDriver(rideId,rating), HttpStatus.OK);
    }

}
