package org.spring.demo.uberapp.strategies;

import org.spring.demo.uberapp.dto.RideRequestDto;
import org.spring.demo.uberapp.entities.Driver;

import java.util.List;

public interface DriverMatchingStrategy {

    List<Driver> findMatchingDriver(RideRequestDto rideRequestDto);

}
