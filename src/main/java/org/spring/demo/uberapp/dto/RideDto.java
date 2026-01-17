package org.spring.demo.uberapp.dto;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;
import org.spring.demo.uberapp.entities.Driver;
import org.spring.demo.uberapp.entities.Rider;
import org.spring.demo.uberapp.entities.enums.PaymentMethod;
import org.spring.demo.uberapp.entities.enums.RideStatus;

import java.time.LocalDateTime;

public class RideDto {


    private Long id;

    private Point pickupLocation;

    private Point dropLocation;

    private LocalDateTime createdTime;

    private RiderDto rider;
    private DriverDto driver;
    private String otp;

    private PaymentMethod paymentMethod;

    private RideStatus rideStatus;

    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

}
