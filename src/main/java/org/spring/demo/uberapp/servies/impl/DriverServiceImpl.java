package org.spring.demo.uberapp.servies.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.spring.demo.uberapp.dto.DriverDto;
import org.spring.demo.uberapp.dto.RideDto;
import org.spring.demo.uberapp.dto.RiderDto;
import org.spring.demo.uberapp.entities.Driver;
import org.spring.demo.uberapp.entities.Ride;
import org.spring.demo.uberapp.entities.RideRequest;
import org.spring.demo.uberapp.entities.enums.RideRequestStatus;
import org.spring.demo.uberapp.exceptions.ResourceNotFoundException;
import org.spring.demo.uberapp.exceptions.RuntimeConflictException;
import org.spring.demo.uberapp.repositories.DriverRepository;
import org.spring.demo.uberapp.servies.DriverService;
import org.spring.demo.uberapp.servies.RideRequestService;
import org.spring.demo.uberapp.servies.RideService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public RideDto acceptRide(Long rideRequestId) {

        RideRequest rideRequestById = rideRequestService.findRideRequestById(rideRequestId);
        Driver currentDriver = getCurrentDriver();

        if(!rideRequestById.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RuntimeConflictException("Ride request status is not pending :"+rideRequestById.getRideRequestStatus());
        }

        if(!currentDriver.isAvailable()){
            throw new RuntimeException("Driver is not available to accept the ride");
        }

        Ride newRide = rideService.createNewRide(rideRequestById, currentDriver);
        return modelMapper.map(newRide,RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto startRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository
                .findById(2L)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with the driver id"+2L));
    }
}
