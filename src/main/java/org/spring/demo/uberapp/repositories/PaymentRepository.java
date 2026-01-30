package org.spring.demo.uberapp.repositories;

import org.spring.demo.uberapp.entities.Payment;
import org.spring.demo.uberapp.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    Optional<Payment> findByRide(Ride ride);

}
