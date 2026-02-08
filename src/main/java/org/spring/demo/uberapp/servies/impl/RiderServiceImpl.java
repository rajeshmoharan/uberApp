package org.spring.demo.uberapp.servies.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.spring.demo.uberapp.dto.DriverDto;
import org.spring.demo.uberapp.dto.RideDto;
import org.spring.demo.uberapp.dto.RideRequestDto;
import org.spring.demo.uberapp.dto.RiderDto;
import org.spring.demo.uberapp.entities.*;
import org.spring.demo.uberapp.entities.enums.RideRequestStatus;
import org.spring.demo.uberapp.entities.enums.RideStatus;
import org.spring.demo.uberapp.exceptions.ResourceNotFoundException;
import org.spring.demo.uberapp.repositories.RideRequestRepository;
import org.spring.demo.uberapp.repositories.RiderRepository;
import org.spring.demo.uberapp.servies.DriverService;
import org.spring.demo.uberapp.servies.RatingService;
import org.spring.demo.uberapp.servies.RideService;
import org.spring.demo.uberapp.servies.RiderService;
import org.spring.demo.uberapp.strategies.Impl.RideStrategyManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideStrategyManager rideStrategyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final RideService rideService;
    private final DriverService driverService;
    private final RatingService ratingService;

    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        Rider rider = getRiderDetails();
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);

        double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
        rideStrategyManager.driverMatchingStrategy(getRiderDetails().getRating()).findMatchingDriver(rideRequest);

        //TODO send notification to all the drivers

        return modelMapper.map(savedRideRequest,RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {

        Rider currentRider = getCurrentRider();
        Ride rideById = rideService.getRideById(rideId);

        if(!currentRider.equals(rideById.getRider())){
            throw new RuntimeException("Rider does not own this ride with id : "+rideById.getId());
        }

        if(!rideById.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride cannot be cancelled other than Confirm status : "+rideById.getRideStatus());
        }

        Ride ride = rideService.updateRideStatus(rideById, RideStatus.CANCELLED);
        driverService.driverAvailabilityUpdate(rideById.getDriver(),true);

        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        Ride rideById = rideService.getRideById(rideId);
        Rider currentRider = getCurrentRider();

        if(!currentRider.equals(rideById.getRider())){
            throw new RuntimeException("Driver cannot rate a rider as he has not started the ride with the rider");
        }

        if(!rideById.getRideStatus().equals(RideStatus.ENDED)){
            throw new RuntimeException("Ride status is not ENDED hence cannot be rated, status"+rideById.getRideStatus());
        }

        return ratingService.rateDriver(rideById,rating);
    }

    @Override
    public RiderDto getMyProfile() {
        Rider currentRider = getCurrentRider();
        return modelMapper.map(currentRider,RiderDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        Rider currentRider = getCurrentRider();
        return rideService.getAllRidesOfRider(currentRider,pageRequest)
                .map(ride -> modelMapper.map(ride,RideDto.class));
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider
                .builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getRiderDetails() {
         return riderRepository.findById(1L)
                 .orElseThrow(() -> new ResourceNotFoundException("Rider not found with the details"));

    }

    @Override
    public Rider getCurrentRider() {
        return riderRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Rider not available with the id "));
    }
}
