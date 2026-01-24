package org.spring.demo.uberapp.strategies.Impl;

import lombok.RequiredArgsConstructor;
import org.spring.demo.uberapp.entities.RideRequest;
import org.spring.demo.uberapp.servies.DistanceService;
import org.spring.demo.uberapp.strategies.RideFareCalculationStrategy;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceService;
    private final static double SURGE_FACTOR=2;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(), rideRequest.getDropLocation());
        return distance * RIDE_FARE_MULTIPLIER*SURGE_FACTOR;
    }
}
