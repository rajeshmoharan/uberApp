package org.spring.demo.uberapp.strategies;

import org.spring.demo.uberapp.dto.RideRequestDto;

public interface RideFareCalculationStrategy {

    double calculateFare(RideRequestDto rideRequestDto);

}
