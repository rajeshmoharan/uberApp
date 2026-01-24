package org.spring.demo.uberapp.servies;

import org.spring.demo.uberapp.dto.DriverDto;
import org.spring.demo.uberapp.dto.RideDto;
import org.spring.demo.uberapp.dto.RideRequestDto;
import org.spring.demo.uberapp.dto.RiderDto;
import org.spring.demo.uberapp.entities.Rider;
import org.spring.demo.uberapp.entities.User;

import java.util.List;

public interface RiderService {

    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideRequestDto cancelRide(RideRequestDto rideRequestDto);

    DriverDto rateDriver(Long rideId, Integer rating);

    RiderDto getMyProfile();

    List<RideDto> getAllMyRides();

    Rider createNewRider(User user);

}
