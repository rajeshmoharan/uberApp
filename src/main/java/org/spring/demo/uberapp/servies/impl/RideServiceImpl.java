package org.spring.demo.uberapp.servies.impl;

import org.spring.demo.uberapp.dto.DriverDto;
import org.spring.demo.uberapp.dto.RideDto;
import org.spring.demo.uberapp.dto.RideRequestDto;
import org.spring.demo.uberapp.dto.RiderDto;
import org.spring.demo.uberapp.entities.Driver;
import org.spring.demo.uberapp.entities.Ride;
import org.spring.demo.uberapp.entities.enums.RideStatus;
import org.spring.demo.uberapp.servies.RideService;
import org.spring.demo.uberapp.servies.RiderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideServiceImpl implements RideService {


    @Override
    public Ride getRideById(Long rideId) {
        return null;
    }

    @Override
    public void matchWithDrivers(RideRequestDto rideRequestDto) {

    }

    @Override
    public Ride createNewRide(RideRequestDto rideRequestDto, Driver driver) {
        return null;
    }

    @Override
    public Ride updateRideStatus(Long rideId, RideStatus rideStatus) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest) {
        return null;
    }
}
