package org.spring.demo.uberapp.strategies.Impl;

import lombok.RequiredArgsConstructor;
import org.spring.demo.uberapp.dto.RideRequestDto;
import org.spring.demo.uberapp.entities.RideRequest;
import org.spring.demo.uberapp.servies.DistanceService;
import org.spring.demo.uberapp.strategies.RideFareCalculationStrategy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RiderFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceService;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(), rideRequest.getDropLocation());
        return distance * RIDE_FARE_MULTIPLIER;
    }
}
