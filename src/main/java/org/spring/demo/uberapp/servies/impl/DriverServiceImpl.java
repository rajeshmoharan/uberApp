package org.spring.demo.uberapp.servies.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.spring.demo.uberapp.dto.DriverDto;
import org.spring.demo.uberapp.dto.RideDto;
import org.spring.demo.uberapp.dto.RiderDto;
import org.spring.demo.uberapp.entities.Driver;
import org.spring.demo.uberapp.entities.Ride;
import org.spring.demo.uberapp.entities.RideRequest;
import org.spring.demo.uberapp.entities.enums.RideRequestStatus;
import org.spring.demo.uberapp.entities.enums.RideStatus;
import org.spring.demo.uberapp.exceptions.ResourceNotFoundException;
import org.spring.demo.uberapp.exceptions.RuntimeConflictException;
import org.spring.demo.uberapp.repositories.DriverRepository;
import org.spring.demo.uberapp.servies.DriverService;
import org.spring.demo.uberapp.servies.PaymentService;
import org.spring.demo.uberapp.servies.RideRequestService;
import org.spring.demo.uberapp.servies.RideService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;
    private final PaymentService paymentService;

    @Override
    @Transactional
    public RideDto acceptRide(Long rideRequestId) {

        RideRequest rideRequestById = rideRequestService.findRideRequestById(rideRequestId);
        Driver currentDriver = getCurrentDriver();

        if(!rideRequestById.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RuntimeConflictException("Ride request status is not pending :"+rideRequestById.getRideRequestStatus());
        }

        if(!currentDriver.isAvailable()){
            throw new RuntimeException("Driver is not available to accept the ride");
        }

        currentDriver.setAvailable(false);
        Driver savedDriver = driverRepository.save(currentDriver);

        Ride newRide = rideService.createNewRide(rideRequestById, savedDriver);
        return modelMapper.map(newRide,RideDto.class);
    }

    @Override
    @Transactional
    public RideDto cancelRide(Long rideId) {

        Ride rideById = rideService.getRideById(rideId);
        Driver currentDriver = getCurrentDriver();

        if(!currentDriver.equals(rideById.getDriver())){
            throw new RuntimeException("Driver cannot start a ride as he has not started earlier");
        }

        if(!rideById.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride cannot be cancelled other than Confirm status : "+rideById.getRideStatus());
        }

        rideService.updateRideStatus(rideById,RideStatus.CANCELLED);
        driverAvailabilityUpdate(currentDriver,true);

        return modelMapper.map(rideById, RideDto.class);
    }

    @Override
    @Transactional
    public RideDto startRide(Long rideId,String otp) {

        Ride rideById = rideService.getRideById(rideId);
        Driver currentDriver = getCurrentDriver();

        if(!currentDriver.equals(rideById.getDriver())){
            throw new RuntimeException("Driver cannot start a ride as he has not started earlier");
        }

        if(!rideById.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride status is not CONFIRMED hence cannot be started, status"+rideById.getRideStatus());
        }

        if(!rideById.getOtp().equals(otp)){
            throw new RuntimeException("Otp is not valid,otp"+otp);
        }

        rideById.setStartedAt(LocalDateTime.now());
        Ride ride = rideService.updateRideStatus(rideById, RideStatus.ONGOING);
        currentDriver.setAvailable(false);

        paymentService.createNewPayment(ride);

        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    @Transactional
    public RideDto endRide(Long rideId) {

        Ride rideById = rideService.getRideById(rideId);
        Driver currentDriver = getCurrentDriver();

        if(!currentDriver.equals(rideById.getDriver())){
            throw new RuntimeException("Driver cannot start a ride as he has not started earlier");
        }

        if(!rideById.getRideStatus().equals(RideStatus.ONGOING)){
            throw new RuntimeException("Ride status is not ONGOING hence cannot be ended, status"+rideById.getRideStatus());
        }

        rideById.setEndedAt(LocalDateTime.now());
        Ride ride = rideService.updateRideStatus(rideById, RideStatus.ENDED);
        driverAvailabilityUpdate(currentDriver,true);

        paymentService.processPayment(rideById);

        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        Driver currentDriver = getCurrentDriver();
        return modelMapper.map(currentDriver, DriverDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        Driver currentDriver = getCurrentDriver();
        return rideService
                .getAllRidesOfDriver(currentDriver, pageRequest)
                .map(ride -> modelMapper.map(ride,RideDto.class));
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository
                .findById(2L)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with the driver id"+2L));
    }

    @Override
    public Driver driverAvailabilityUpdate(Driver driver, boolean status) {
        driver.setAvailable(true);
        return driverRepository.save(driver);
    }

    @Override
    public Driver createNewDriver(Driver driver) {
        return driverRepository.save(driver);
    }
}
