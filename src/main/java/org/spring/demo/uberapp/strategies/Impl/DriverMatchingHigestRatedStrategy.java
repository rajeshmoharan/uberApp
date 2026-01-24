package org.spring.demo.uberapp.strategies.Impl;

import lombok.RequiredArgsConstructor;
import org.spring.demo.uberapp.dto.RideRequestDto;
import org.spring.demo.uberapp.entities.Driver;
import org.spring.demo.uberapp.entities.RideRequest;
import org.spring.demo.uberapp.repositories.DriverRepository;
import org.spring.demo.uberapp.strategies.DriverMatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverMatchingHigestRatedStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        return driverRepository.findTenNearbyTopRatedDrivers(rideRequest.getPickupLocation());
    }
}
