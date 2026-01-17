package org.spring.demo.uberapp.servies.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.spring.demo.uberapp.dto.DriverDto;
import org.spring.demo.uberapp.dto.RideDto;
import org.spring.demo.uberapp.dto.RideRequestDto;
import org.spring.demo.uberapp.dto.RiderDto;
import org.spring.demo.uberapp.entities.RideRequest;
import org.spring.demo.uberapp.entities.enums.RideRequestStatus;
import org.spring.demo.uberapp.servies.RiderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);

        log.info("Ride request {}",rideRequest);
        return null;
    }

    @Override
    public RideRequestDto cancelRide(RideRequestDto rideRequestDto) {
        return null;
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }
}
