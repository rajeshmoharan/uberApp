package org.spring.demo.uberapp.servies;

import org.spring.demo.uberapp.entities.RideRequest;

public interface RideRequestService {

    RideRequest findRideRequestById(Long rideRequestId);

    void update(RideRequest rideRequest);
}
