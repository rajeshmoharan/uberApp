package org.spring.demo.uberapp.strategies;

import org.spring.demo.uberapp.entities.RideRequest;

public interface RideFareCalculationStrategy {

    int RIDE_FARE_MULTIPLIER = 10;

    double calculateFare(RideRequest rideRequest);

}
