package org.spring.demo.uberapp.servies;

import org.spring.demo.uberapp.dto.DriverDto;
import org.spring.demo.uberapp.dto.RideDto;
import org.spring.demo.uberapp.dto.RideRequestDto;
import org.spring.demo.uberapp.dto.RiderDto;
import org.spring.demo.uberapp.entities.Rider;
import org.spring.demo.uberapp.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RiderService {

    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    DriverDto rateDriver(Long rideId, Integer rating);

    RiderDto getMyProfile();

    Page<RideDto> getAllMyRides(PageRequest pageRequest);

    Rider createNewRider(User user);

    Rider getRiderDetails();

    Rider getCurrentRider();
}
