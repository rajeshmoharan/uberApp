package org.spring.demo.uberapp.repositories;

import org.spring.demo.uberapp.entities.Driver;
import org.spring.demo.uberapp.entities.Rating;
import org.spring.demo.uberapp.entities.Ride;
import org.spring.demo.uberapp.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {

    List<Rating> findByRider(Rider rider);

    List<Rating> findByDriver(Driver driver);

    Optional<Rating> findByRide(Ride ride);
}
