package org.spring.demo.uberapp.repositories;

import org.locationtech.jts.geom.Point;
import org.spring.demo.uberapp.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//ST_DISTANCE(point1,point2)
//ST_D

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {

    @Query(
            value = """
        SELECT d.*, 
               ST_Distance(d.current_location, :pickupLocation) AS distance
        FROM drivers d
        WHERE d.available = true
          AND ST_DWithin(d.current_location, :pickupLocation, 10000)
        ORDER BY distance
        LIMIT 10
        """,
            nativeQuery = true
    )
    List<Driver> findNearestDrivers(Point pickupLocation);


    @Query(value = "SELECT d.* " +
            "FROM drivers d WHERE d.available = true AND ST_DWithin(d.current_location:pickupLocation,150000)" +
            "ORDER BY rating LIMIT 10",nativeQuery = true)
    List<Driver> findTenNearbyTopRatedDrivers(Point pickupLocation);
}
