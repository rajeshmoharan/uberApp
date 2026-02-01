package org.spring.demo.uberapp.strategies.Impl;

import lombok.RequiredArgsConstructor;
import org.spring.demo.uberapp.strategies.DriverMatchingStrategy;
import org.spring.demo.uberapp.strategies.RideFareCalculationStrategy;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {

    private final  DriverMatchingHigestRatedStrategy driverMatchingHighestRatedStrategy;
    private final  DriverMatchingNearestDriverStrategy driverMatchingNearestDriverStrategy;
    private final  RiderFareDefaultFareCalculationStrategy riderFareDefaultFareCalculationStrategy;
    private final RideFareSurgePricingFareCalculationStrategy rideFareSurgePricingFareCalculationStrategy;

    public DriverMatchingStrategy driverMatchingStrategy(double rating){
            if(rating >= 4.8){
                return driverMatchingHighestRatedStrategy;
            }else {
                return  driverMatchingNearestDriverStrategy;
            }
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy(){

        LocalTime surgeStartTime = LocalTime.of(18,0,0);
        LocalTime surgeEndTime = LocalTime.of(21,0,0);
        LocalTime currentTime = LocalTime.now();

        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);

        if(isSurgeTime){
            return rideFareSurgePricingFareCalculationStrategy;
        }else {
            return riderFareDefaultFareCalculationStrategy;
        }
    }

}
