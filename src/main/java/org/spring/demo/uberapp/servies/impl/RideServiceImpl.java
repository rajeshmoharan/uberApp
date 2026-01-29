package org.spring.demo.uberapp.servies.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.spring.demo.uberapp.dto.RideRequestDto;
import org.spring.demo.uberapp.entities.Driver;
import org.spring.demo.uberapp.entities.Ride;
import org.spring.demo.uberapp.entities.RideRequest;
import org.spring.demo.uberapp.entities.Rider;
import org.spring.demo.uberapp.entities.enums.RideRequestStatus;
import org.spring.demo.uberapp.entities.enums.RideStatus;
import org.spring.demo.uberapp.exceptions.RuntimeConflictException;
import org.spring.demo.uberapp.repositories.RideRepository;
import org.spring.demo.uberapp.servies.RideRequestService;
import org.spring.demo.uberapp.servies.RideService;
import org.spring.demo.uberapp.util.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;
    private final ModelMapper modelMapper;
    private final RideRequestService rideRequestService;


    @Override
    public Ride getRideById(Long rideId) {
        return rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found with the ride Id"+rideId));
    }

    @Override
    public void matchWithDrivers(RideRequestDto rideRequestDto) {

    }

    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
        try{
            rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);

            Ride ride = modelMapper.map(rideRequest, Ride.class);
            ride.setId(null);
            ride.setRideStatus(RideStatus.CONFIRMED);
            ride.setDriver(driver);
            ride.setOtp(Utils.generateOtp());

            rideRequestService.update(rideRequest);
            return rideRepository.save(ride);

        } catch (Exception e) {
            throw new RuntimeConflictException("Unable to create ride right now "+e.getMessage());
        }
    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
        ride.setRideStatus(rideStatus);
        return rideRepository.save(ride);
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest) {
        return rideRepository.findByRider(rider,pageRequest);
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest) {
        return rideRepository.findByDriver(driver,pageRequest);
    }
}
