package org.spring.demo.uberapp.servies;

import org.spring.demo.uberapp.dto.RideRequestDto;
import org.spring.demo.uberapp.entities.Driver;
import org.spring.demo.uberapp.entities.Ride;
import org.spring.demo.uberapp.entities.RideRequest;
import org.spring.demo.uberapp.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long rideId);

    void matchWithDrivers(RideRequestDto rideRequestDto);

    Ride createNewRide(RideRequest rideRequestDto, Driver driver);

    Ride updateRideStatus(Long rideId, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Long driverId,PageRequest pageRequest);

}
