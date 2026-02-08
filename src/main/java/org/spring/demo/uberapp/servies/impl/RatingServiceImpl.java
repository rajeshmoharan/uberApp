package org.spring.demo.uberapp.servies.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.spring.demo.uberapp.dto.DriverDto;
import org.spring.demo.uberapp.dto.RiderDto;
import org.spring.demo.uberapp.entities.Driver;
import org.spring.demo.uberapp.entities.Rating;
import org.spring.demo.uberapp.entities.Ride;
import org.spring.demo.uberapp.entities.Rider;
import org.spring.demo.uberapp.repositories.DriverRepository;
import org.spring.demo.uberapp.repositories.RatingRepository;
import org.spring.demo.uberapp.repositories.RiderRepository;
import org.spring.demo.uberapp.servies.RatingService;
import org.springframework.stereotype.Service;

import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final ModelMapper modelMapper;

    @Override
    public DriverDto rateDriver(Ride ride, Integer rating) {

        Driver driver = ride.getDriver();

        Rating ratingRepo = ratingRepository.findByRide(ride)
                .orElseThrow(() -> new RuntimeException("Rating not found for the given ride"));

         if(ratingRepo.getDriverRating() != null){
            throw new RuntimeException("Driver has already been rated for this ride");
         }

        ratingRepo.setDriverRating(rating);

        ratingRepository.save(ratingRepo);

        OptionalDouble average = ratingRepository.findByDriver(driver)
                .stream()
                .mapToDouble(Rating::getDriverRating)
                .average();

        driver.setRating(average.orElse(0.0));

        Driver save = driverRepository.save(driver);
        return modelMapper.map(save, DriverDto.class);

    }

    @Override
    public RiderDto rateRider(Ride ride, Integer rating) {

        Rider rider = ride.getRider();

        Rating ratingRepo = ratingRepository.findByRide(ride)
                .orElseThrow(() -> new RuntimeException("Rating not found for the given ride"));

        if(ratingRepo.getRiderRating() != null){
            throw new RuntimeException("Rider has already been rated for this ride");
        }

        ratingRepo.setRiderRating(rating);

        ratingRepository.save(ratingRepo);

        OptionalDouble average = ratingRepository.findByRider(rider)
                .stream()
                .mapToDouble(Rating::getRiderRating)
                .average();

        rider.setRating(average.orElse(0.0));

        Rider save = riderRepository.save(rider);
        return modelMapper.map(save, RiderDto.class);

    }

    @Override
    public void createRatingForRide(Ride ride) {
        Rating ratingBuilder = Rating.builder()
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .ride(ride)
                .build();
        ratingRepository.save(ratingBuilder);
    }
}
