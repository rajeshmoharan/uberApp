package org.spring.demo.uberapp.servies.impl;

import lombok.RequiredArgsConstructor;
import org.spring.demo.uberapp.entities.RideRequest;
import org.spring.demo.uberapp.exceptions.ResourceNotFoundException;
import org.spring.demo.uberapp.repositories.RideRequestRepository;
import org.spring.demo.uberapp.servies.RideRequestService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {

        return rideRequestRepository
                .findById(rideRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Ride not found with the rideRequest Id : "+rideRequestId));

    }

    @Override
    public void update(RideRequest rideRequest) {

        rideRequestRepository.findById(rideRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Ride request not exit with the id"+rideRequest.getId()));
        rideRequestRepository.save(rideRequest);

    }
}
