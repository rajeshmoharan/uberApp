package org.spring.demo.uberapp.servies;

import org.spring.demo.uberapp.entities.Payment;
import org.spring.demo.uberapp.entities.Ride;

public interface PaymentService {

    void processPayment(Payment payment);

    Payment createNewPayment(Ride ride);
}
