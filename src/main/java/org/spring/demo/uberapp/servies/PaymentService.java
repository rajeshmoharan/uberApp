package org.spring.demo.uberapp.servies;

import org.spring.demo.uberapp.entities.Payment;
import org.spring.demo.uberapp.entities.Ride;
import org.spring.demo.uberapp.entities.enums.PaymentStatus;

public interface PaymentService {

    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}
