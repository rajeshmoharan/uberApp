package org.spring.demo.uberapp.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;
import org.spring.demo.uberapp.entities.Rider;
import org.spring.demo.uberapp.entities.enums.PaymentMethod;
import org.spring.demo.uberapp.entities.enums.RideRequestStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDto {

    private Long id;
    private PointDto pickupLocation;
    private PointDto dropLocation;
    private LocalDateTime requestedTime;
    private RiderDto rider;
    private PaymentMethod paymentMethod;
    private RideRequestStatus rideRequestStatus;

}
