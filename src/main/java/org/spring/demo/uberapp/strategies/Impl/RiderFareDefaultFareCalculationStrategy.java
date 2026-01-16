package org.spring.demo.uberapp.strategies.Impl;

import org.spring.demo.uberapp.dto.RideRequestDto;
import org.spring.demo.uberapp.strategies.RideFareCalculationStrategy;

public class RiderFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {
    @Override
    public double calculateFare(RideRequestDto rideRequestDto) {
        return 0;
    }
}
