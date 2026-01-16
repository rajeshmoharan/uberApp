package org.spring.demo.uberapp.strategies.Impl;

import org.spring.demo.uberapp.dto.RideRequestDto;
import org.spring.demo.uberapp.entities.Driver;
import org.spring.demo.uberapp.strategies.DriverMatchingStrategy;

import java.util.List;

public class DriverMatchingHigestRatedStrategy implements DriverMatchingStrategy {

    @Override
    public List<Driver> findMatchingDriver(RideRequestDto rideRequestDto) {
        return List.of();
    }
}
