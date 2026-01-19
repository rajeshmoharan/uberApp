package org.spring.demo.uberapp.strategies.Impl;

import org.spring.demo.uberapp.dto.RideRequestDto;
import org.spring.demo.uberapp.entities.RideRequest;
import org.spring.demo.uberapp.strategies.RideFareCalculationStrategy;
import org.springframework.stereotype.Service;


public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {

    @Override
    public double calculateFare(RideRequest rideRequest) {
        return 0;
    }
}
